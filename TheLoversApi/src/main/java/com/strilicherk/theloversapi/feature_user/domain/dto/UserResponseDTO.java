package com.strilicherk.theloversapi.feature_user.domain.dto;

import com.strilicherk.theloversapi.domain.gender.Gender;
import com.strilicherk.theloversapi.domain.user_location.UserLocationRequestDTO;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String name,
        Boolean userVerified,
        LocalDate birthDate,
        UserLocationRequestDTO userLocation,
        Gender gender
) {
}
