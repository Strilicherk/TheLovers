package com.strilicherk.theloversapi.feature_user.domain.dto;

import com.strilicherk.theloversapi.core.domain.model.user_location.UserLocationRequestDTO;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        String email,
        String phone,
        Boolean userVerified,
        LocalDate birthDate,
        Integer genderId,
        UserLocationRequestDTO userLocation,
        Integer roleId
) {
}
