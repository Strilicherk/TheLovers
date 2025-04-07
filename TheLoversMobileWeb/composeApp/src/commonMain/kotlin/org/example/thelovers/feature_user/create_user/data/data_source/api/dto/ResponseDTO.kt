package org.example.thelovers.feature_user.create_user.data.data_source.api.dto

data class ResponseDTO<T>(
    val status: Int,
    val message: String,
    val success: Boolean,
    val data: T
)
