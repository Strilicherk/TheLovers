package com.strilicherk.theloversapi.services.login;

import com.strilicherk.theloversapi.domain.login.LoginDTO;
import com.strilicherk.theloversapi.domain.shared.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    private final TokenService tokenService;
    private final PhoneVerificationService phoneVerificationService;
    private final RefreshTokenService refreshTokenService;

    @Autowired
    public LoginService(TokenService tokenService,
                        RefreshTokenService refreshTokenService,
                        PhoneVerificationService phoneVerificationService
    ) {
        this.tokenService = tokenService;
        this.refreshTokenService = refreshTokenService;
        this.phoneVerificationService = phoneVerificationService;
    }

    public ResponseDTO<Map<String, String>> login(LoginDTO loginDTO) {
        String phone = loginDTO.phone();
        String verificationCode = loginDTO.verificationCode();
        String deviceId = loginDTO.deviceId();

        try {
            ResponseDTO<String> verificationStatus = phoneVerificationService.verifyPhone(phone, verificationCode);
            if (!verificationStatus.success()) {
                return new ResponseDTO<>(verificationStatus.status(), verificationStatus.message(), false, null);
            }

            String refreshToken = tokenService.generateRefreshToken(phone, deviceId);
            refreshTokenService.storeRefreshTokenForUser(phone, refreshToken, deviceId);
            String jwtToken = tokenService.generateJWT(phone, deviceId);

            Map<String, String> tokens = new HashMap<>();
            tokens.put("jwtToken", jwtToken);
            tokens.put("refreshToken", refreshToken);

            return new ResponseDTO<>(200, "Successfully login", true, tokens);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

