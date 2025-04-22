package com.strilicherk.theloversapi.core.security.jwt;

import com.strilicherk.theloversapi.core.repositories.RefreshTokenRepository;
import com.strilicherk.theloversapi.core.domain.model.user_refresh_token.RefreshToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@Slf4j
public class JwtTokenValidator implements TokenValidator {
    @Value("${SECRET_KEY}")
    private String secretKey;
    private Key signingKey;

    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    public JwtTokenValidator(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @PostConstruct
    public void init() {
        if (secretKey == null || secretKey.isBlank()) {
            throw new IllegalArgumentException("SECRET_KEY must be configured.");
        }
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean isJwtTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getExpiration().after(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public boolean isRefreshTokenValid (String refreshToken, String deviceId) {
        RefreshToken tokenInfo = refreshTokenRepository.findByRefreshToken(refreshToken).orElse(null);

        if (tokenInfo == null) {
            log.info("Refresh token not found");
            return false;
        }

        if (!tokenInfo.getDeviceId().equals(deviceId)) {
            log.info("Invalid device ID for refresh token");
            return false;
        }

        if (tokenInfo.getExpiresAt().isBefore(LocalDateTime.now())) {
            log.info("Refresh token expired");
            return false;
        }

        return true;
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String extractRole(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.get("role", String.class);
        } catch (JwtException e) {
            log.error("Error extracting role from token: {}", e.getMessage());
            return null;
        }
    }

    @Override
    public String extractPhone(String token) {
        try {
            Claims claims = getClaims(token);
            return claims.getSubject();
        } catch (JwtException e) {
            log.error("Error extracting phone from token: {}", e.getMessage());
            return null;
        }
    }
}
