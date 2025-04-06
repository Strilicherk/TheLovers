package com.strilicherk.matchapp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenGenerator {
    @Value("${SECRET_KEY}")
    private String secretKey;

    public String generateToken(String phone, long expirationTime, String deviceId) {
        return Jwts.builder()
                .setSubject(phone)
                .setSubject(deviceId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public String generateAdminToken() {
        return Jwts.builder()
                .setSubject("admin")
                .claim("role", "ADMIN")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))  // Token expira em 24h
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
