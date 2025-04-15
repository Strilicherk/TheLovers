package com.strilicherk.theloversapi.feature_auth.login.domain.model;

import com.strilicherk.theloversapi.feature_user.domain.dto.UserResponseDTO;

public record UserResult(Boolean newUser, UserResponseDTO user) {
}
