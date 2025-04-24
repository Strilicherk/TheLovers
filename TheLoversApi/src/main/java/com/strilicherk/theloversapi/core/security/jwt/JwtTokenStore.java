package com.strilicherk.theloversapi.core.security.jwt;

import com.strilicherk.theloversapi.core.repositories.RefreshTokenRepository;
import com.strilicherk.theloversapi.core.domain.model.user_refresh_token.RefreshToken;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class JwtTokenStore implements TokenStore {
    private final RefreshTokenRepository refreshTokenRepository;

    public JwtTokenStore(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    @Transactional
    public void storeRefreshToken(UUID userId, String refreshToken, String deviceId) {
        refreshTokenRepository.deleteByUserIdAndDeviceId(userId, deviceId);
        RefreshToken token = new RefreshToken();
        token.setUserId(userId);
        token.setDeviceId(deviceId);
        token.setRefreshToken(refreshToken);
        token.setExpiresAt(LocalDateTime.now().plusDays(30));
        refreshTokenRepository.save(token);
    }
}
