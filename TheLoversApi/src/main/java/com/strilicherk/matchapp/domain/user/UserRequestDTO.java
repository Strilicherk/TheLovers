package com.strilicherk.matchapp.domain.user;

import com.strilicherk.matchapp.domain.user_location.UserLocationRequestDTO;

import java.time.LocalDate;
import java.util.UUID;

public record UserRequestDTO(
        UUID id, String name,
        Boolean userVerified,
        LocalDate birthDate,
        UserLocationRequestDTO userLocation
) { }
