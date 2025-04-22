package com.strilicherk.theloversapi.core.security.jwt;

import com.strilicherk.theloversapi.feature_user.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class JwtTokenGenerator implements TokenGenerator {
    private static final long JWT_EXPIRATION_TIME = Duration.ofDays(1).toMillis();
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = Duration.ofDays(30).toMillis();

    @Value("${SECRET_KEY}")
    private String secretKey;
    private Key signingKey;

    private final UserRepository userRepository;

    @Autowired
    public JwtTokenGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        if (secretKey == null || secretKey.isBlank()) {
            throw new IllegalArgumentException("SECRET_KEY must be configured.");
        }
        if (secretKey.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new IllegalArgumentException("SECRET_KEY must be at least 256 bits (32 bytes).");
        }
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String generateJwtToken(UUID userId) {
        String role = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId))
                .getRole()
                .getName()
                .name();

        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateRefreshToken(UUID userId, String deviceId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("deviceId", deviceId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
