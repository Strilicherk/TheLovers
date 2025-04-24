package org.example.thelovers.feature_validate_session.domain

data class Tokens(
    val refreshToken: String,
    val jwtToken: String
)
