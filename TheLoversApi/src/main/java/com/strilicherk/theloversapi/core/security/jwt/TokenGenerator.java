package com.strilicherk.theloversapi.core.security.jwt;

import java.util.UUID;

public interface TokenGenerator {
    String generateJwtToken(UUID userId);
    String generateRefreshToken(UUID userId, String deviceId);
}
