package com.strilicherk.theloversapi.feature_auth.domain.sms.dto;

import com.strilicherk.theloversapi.feature_user.domain.dto.UserResponseDTO;

public record PhoneVerificationResponseDTO(
        String refreshToken,
        String jwtToken,
        UserResponseDTO user
) {
}
