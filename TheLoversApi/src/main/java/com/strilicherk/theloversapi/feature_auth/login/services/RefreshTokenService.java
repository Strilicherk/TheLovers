package com.strilicherk.theloversapi.feature_auth.login.services;

import com.strilicherk.theloversapi.feature_auth.login.repositories.RefreshTokenRepository;
import com.strilicherk.theloversapi.feature_auth.login.authentication.RefreshToken;
import com.strilicherk.theloversapi.feature_user.repositories.UserRepository;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public void storeRefreshTokenForUser(String phone, String refreshToken, String deviceId) {
        userRepository.findByPhone(phone).ifPresent(user -> {
            refreshTokenRepository.deleteByUserIdAndDeviceId(user.getId(), deviceId);
            RefreshToken token = new RefreshToken();
            token.setUserId(user.getId());
            token.setDeviceId(deviceId);
            token.setRefreshToken(refreshToken);
            token.setExpiresAt(LocalDateTime.now().plusDays(30));
            refreshTokenRepository.save(token);
        });
    }

    public RefreshToken validateAndGetRefreshToken(String refreshToken, String deviceId) {
        RefreshToken tokenInfo = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new JwtException("Invalid refresh token"));

        if (!tokenInfo.getDeviceId().equals(deviceId)) {
            throw new JwtException("Invalid device ID for refresh token");
        }

        if (tokenInfo.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new JwtException("Refresh token expired");
        }

        return tokenInfo;
    }
}
