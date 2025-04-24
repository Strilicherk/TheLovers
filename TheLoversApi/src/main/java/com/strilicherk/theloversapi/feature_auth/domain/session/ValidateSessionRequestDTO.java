package com.strilicherk.theloversapi.feature_auth.domain.session;

import java.util.UUID;

public record ValidateSessionRequestDTO(
        String refreshToken,
        UUID userId,
        String deviceId
) {
}
