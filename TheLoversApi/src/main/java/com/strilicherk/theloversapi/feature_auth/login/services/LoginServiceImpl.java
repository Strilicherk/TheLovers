package com.strilicherk.theloversapi.feature_auth.login.services;

import com.strilicherk.theloversapi.core.domain.mappers.UserMapper;
import com.strilicherk.theloversapi.feature_auth.login.domain.dto.LoginResponseDTO;
import com.strilicherk.theloversapi.feature_auth.login.domain.model.Tokens;
import com.strilicherk.theloversapi.feature_auth.login.domain.model.UserResult;
import com.strilicherk.theloversapi.feature_auth.login.exceptions.TokenGenerationException;
import com.strilicherk.theloversapi.exceptions.GenericException;
import com.strilicherk.theloversapi.exceptions.VerificationException;
import com.strilicherk.theloversapi.feature_auth.login.domain.dto.LoginRequestDTO;
import com.strilicherk.theloversapi.domain.shared.ResponseDTO;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserResponseDTO;
import com.strilicherk.theloversapi.feature_user.domain.model.User;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserCreateDTO;
import com.strilicherk.theloversapi.feature_user.repositories.UserRepository;
import com.strilicherk.theloversapi.feature_user.services.UserService;
import com.twilio.exception.ApiException;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Value("${twilio.serviceSid}")
    private String serviceSid;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public LoginServiceImpl(TokenService tokenService,
                            RefreshTokenService refreshTokenService,
                            UserService userService,
                            UserRepository userRepository

    ) {
        this.tokenService = tokenService;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public ResponseDTO<LoginResponseDTO> login(LoginRequestDTO loginRequest) {
        String phone, verificationCode;
        phone = loginRequest.phone();
        verificationCode = loginRequest.verificationCode();

        if (phone == null || phone.isBlank() || verificationCode == null || verificationCode.isBlank()) {
            throw new VerificationException(HttpStatus.BAD_REQUEST, "Phone number and verification code are required.");
        }

        validatePhoneVerification(phone, verificationCode);
        val userInfos = createUserIfNotExists(phone);
        val tokens = generateTokens(loginRequest);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(
                tokens.refreshToken(),
                tokens.jwtToken(),
                userInfos.newUser(),
                userInfos.user()
        );
        return new ResponseDTO<>(200, "Login successful.", true, loginResponseDTO);
    }

    private void validatePhoneVerification(String phone, String verificationCode) {
        try {
            VerificationCheck verificationCheck = VerificationCheck.creator(serviceSid)
                    .setCode(verificationCode)
                    .setTo(phone)
                    .create();
            if ("approved".equals(verificationCheck.getStatus())) {
                log.info("Phone verification status is approved: {}", verificationCheck.getStatus());
            } else {
                log.warn("Phone verification status is not approved: {}", verificationCheck.getStatus());
                throw new VerificationException(HttpStatus.UNAUTHORIZED, "Phone verification failed.");
            }
        } catch (ApiException e) {
            int errorCode = e.getCode();
            if (errorCode == 20404) {
                log.error("Phone verification failed: {}", e.getMessage());
                throw new VerificationException(HttpStatus.NOT_FOUND, "Verification service not found.");
            } else if (errorCode == 22113) {
                log.error("Verification code is rejected: {}", verificationCode);
                throw new VerificationException(HttpStatus.BAD_REQUEST, "Invalid or expired verification code.");
            } else {
                log.error("Internal server error: {}", e.getMessage());
                throw new VerificationException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred in Twilio.");
            }
        } catch (Exception e) {
            log.error("Unexpected exception during phone verification: {}", e.getMessage(), e);
            throw new GenericException("Error occurred during phone verification for phone: " + phone + ". Message: " + e.getMessage());
        }
    }

    private UserResult createUserIfNotExists(String phone) {
        var userOptional = userRepository.findByPhone(phone);

        if (userOptional.isEmpty()) {
            log.info("User does not exist, creating user.");
            User createdUser = userService.createUser(new UserCreateDTO(phone)).data();
            UserResponseDTO userResponseDTO = UserMapper.fromUserToUserResponseDTO(createdUser);
            return new UserResult(false, userResponseDTO);
        } else {
            log.info("User already exists.");
            User user = userOptional.get();
            UserResponseDTO userResponseDTO = UserMapper.fromUserToUserResponseDTO(user);
            return new UserResult(true, userResponseDTO);
        }
    }

    private Tokens generateTokens(LoginRequestDTO loginRequestDTO) {
        String phone = loginRequestDTO.phone();
        String deviceId = loginRequestDTO.deviceId();

        try {
            String refreshToken = tokenService.generateRefreshToken(phone, deviceId);
            refreshTokenService.storeRefreshTokenForUser(phone, refreshToken, deviceId);
            String jwtToken = tokenService.generateJWT(phone, deviceId);

            Map<String, String> tokens = new HashMap<>();
            return new Tokens(refreshToken, jwtToken);
        } catch (JwtException e) {
            log.error("Error while trying to generate tokens for phone {}: {}", phone, e.getMessage(), e);
            throw new TokenGenerationException(
                    "Failed to generate authentication tokens",
                    HttpStatus.UNAUTHORIZED,
                    "Could not generate authentication tokens for the provided phone."
            );
        } catch (DataAccessException e) {
            log.error("Error while storing refresh token in database for phone {}: {}", phone, e.getMessage(), e);
            throw new TokenGenerationException(
                    "Failed to store token in database",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An error occurred while saving the token."
            );

        }
    }
}


