package com.strilicherk.theloversapi.feature_auth.services;

import com.strilicherk.theloversapi.core.security.jwt.TokenGenerator;
import com.strilicherk.theloversapi.core.security.jwt.TokenStore;
import com.strilicherk.theloversapi.core.exceptions.VerificationException;
import com.strilicherk.theloversapi.feature_auth.domain.sms.dto.PhoneVerificationRequestDTO;
import com.strilicherk.theloversapi.feature_auth.domain.sms.dto.PhoneVerificationResponseDTO;
import com.strilicherk.theloversapi.feature_auth.services.sms.PhoneVerificationService;
import com.strilicherk.theloversapi.feature_user.domain.dto.UserCreateDTO;
import com.strilicherk.theloversapi.feature_user.domain.mappers.UserMapper;
import com.strilicherk.theloversapi.feature_user.services.UserService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final TokenGenerator tokenGenerator;
    private final TokenStore tokenStore;
    private final UserService userService;
    private final PhoneVerificationService phoneVerificationService;


    @Autowired
    public AuthenticationServiceImpl(TokenGenerator tokenGenerator,
                                     TokenStore tokenStore,
                                     UserService userService,
                                     PhoneVerificationService phoneVerificationService
    ) {
        this.tokenGenerator = tokenGenerator;
        this.tokenStore = tokenStore;
        this.userService = userService;
        this.phoneVerificationService = phoneVerificationService;
    }

    public PhoneVerificationResponseDTO authenticate(PhoneVerificationRequestDTO loginRequest) {
        String phone, verificationCode;
        phone = loginRequest.phone();
        verificationCode = loginRequest.smsCode();

        if (phone == null || phone.isBlank() || verificationCode == null || verificationCode.isBlank()) {
            log.info("Phone number and verification code are required. Phone: {}, Code: {}", phone,verificationCode);
            throw new VerificationException(HttpStatus.BAD_REQUEST, "Phone number and verification code are required.");
        }

        phoneVerificationService.verifyCode(phone, verificationCode);
        val user = userService.createUser(new UserCreateDTO(phone));
        val refreshToken = tokenGenerator.generateRefreshToken(user.getId(), loginRequest.deviceId());
        val jwtToken = tokenGenerator.generateJwtToken(user.getId());
        tokenStore.storeRefreshToken(user.getId(), refreshToken, loginRequest.deviceId());
        phoneVerificationService.storeSuccessfulVerificationInDatabase(user);

        user.setUserVerified(true);
        user.setUpdatedAt(LocalDateTime.now());
        return new PhoneVerificationResponseDTO(
                refreshToken,
                jwtToken,
                UserMapper.fromUserToUserResponseDto(user)
        );
    }
}


