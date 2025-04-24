package org.example.thelovers.feature_validate_session.data

import kotlinx.serialization.Serializable

@Serializable
data class ValidateSessionResponseDTO(
    val isRefreshTokenValid: Boolean,
    val newRefreshToken: String?,
    val newJwtToken: String?
)
