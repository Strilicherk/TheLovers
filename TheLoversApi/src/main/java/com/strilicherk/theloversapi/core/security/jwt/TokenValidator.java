package com.strilicherk.theloversapi.core.security.jwt;

import io.jsonwebtoken.Claims;

public interface TokenValidator {
    boolean isJwtTokenValid(String token);
    boolean isRefreshTokenValid(String token, String deviceId);
    String extractRole(String token);
    String extractPhone(String token);
    Claims getClaims(String token);
}
