package org.example.thelovers.core.domain

import kotlinx.serialization.Serializable

@Serializable
data class ResponseDTO<T>(
    val status: Int,
    val message: String,
    val success: Boolean,
    val data: T,
)
