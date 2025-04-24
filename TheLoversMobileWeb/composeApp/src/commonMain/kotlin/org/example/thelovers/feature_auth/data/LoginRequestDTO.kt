package org.example.thelovers.feature_auth.data

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(
    val phone: String,
    val smsCode: String,
    val deviceId: String
)
