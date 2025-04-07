package com.strilicherk.matchapp.domain.user;

import java.time.LocalDate;
import java.util.UUID;

public record UserUpdateDTO(
        String name,
        String email,
        String password,
        String phone,
        LocalDate birthDate,
        Integer genderId,
        UUID locationId
) {}
