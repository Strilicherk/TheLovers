package org.example.thelovers.feature_validate_session.data

import kotlinx.serialization.Serializable

@Serializable
data class ValidateSessionRequestDTO(
    val refreshToken: String,
    val userId: String,
    val deviceId: String
)