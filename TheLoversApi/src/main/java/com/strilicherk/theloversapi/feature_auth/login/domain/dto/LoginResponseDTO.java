package com.strilicherk.theloversapi.feature_auth.login.domain.dto;

import com.strilicherk.theloversapi.feature_user.domain.dto.UserResponseDTO;

public record LoginResponseDTO(
        String refreshToken,
        String jwtToken,
        Boolean existingUser,
        UserResponseDTO userResponseDTO
) {
}
