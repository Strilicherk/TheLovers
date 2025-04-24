package org.example.thelovers.feature_auth.data

import kotlinx.serialization.Serializable
import org.example.thelovers.feature_user.data.dto.UserResponseDTO

@Serializable
data class LoginResponseDTO(
    val jwtToken: String,
    val refreshToken: String,
    val user: UserResponseDTO
)
