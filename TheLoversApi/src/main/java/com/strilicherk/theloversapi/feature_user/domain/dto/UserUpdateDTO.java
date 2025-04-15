package com.strilicherk.theloversapi.feature_user.domain.dto;

import com.strilicherk.theloversapi.domain.user_location.UserLocation;

import java.time.LocalDate;
import java.util.UUID;

public record UserUpdateDTO(
        String email,
        String phone
) {}
