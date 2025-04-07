package com.strilicherk.theloversapi.services.login;

import com.strilicherk.theloversapi.domain.authentication.RefreshToken;
import com.strilicherk.theloversapi.domain.user.User;
import com.strilicherk.theloversapi.repositories.RefreshTokenRepository;
import com.strilicherk.theloversapi.repositories.UserRepository;
import io.jsonwebtoken.JwtException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public void storeRefreshTokenForUser(String phone, String refreshToken, String deviceId) {
        Optional<User> user = userRepository.findByPhone(phone);
        if (user.isPresent()) {
            RefreshToken token = new RefreshToken();
            token.setUserId(user.get().getId());
            token.setDeviceId(deviceId);
            token.setRefreshToken(refreshToken);
            token.setExpiresAt(LocalDateTime.now().plusSeconds(2592000)); // 30 dias
            refreshTokenRepository.save(token);
        }
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
