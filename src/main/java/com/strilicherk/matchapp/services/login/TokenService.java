package com.strilicherk.matchapp.services.login;

import com.strilicherk.matchapp.security.TokenGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    private final TokenGenerator tokenGenerator;

    @Value("${SECRET_KEY}")
    private String secretKey;
    private static final long REFRESH_TOKEN_EXPIRATION_TIME = 2592000000L; // 30 days
    private static final long JWT_EXPIRATION_TIME = 86400000; // 1 day

    @Autowired
    public TokenService(TokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    public String generateJWT(String phone, String deviceId) {
        return tokenGenerator.generateToken(phone, JWT_EXPIRATION_TIME, deviceId);
    }

    public String generateRefreshToken(String phone, String deviceId) {
        return tokenGenerator.generateToken(phone, REFRESH_TOKEN_EXPIRATION_TIME, deviceId);
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return !getClaims(token).getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

