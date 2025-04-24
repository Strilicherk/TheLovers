package com.strilicherk.theloversapi.core.security.jwt;

import java.util.UUID;

public interface TokenStore {
    void storeRefreshToken(UUID userId, String refreshToken, String deviceId);
}
