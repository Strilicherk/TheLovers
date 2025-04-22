package com.strilicherk.theloversapi.feature_auth.domain.session;

public record ValidateSessionResponseDTO(
        Boolean isRefreshTokenValid,
        String newRefreshToken,
        String newJwtToken
) {
}
