package org.example.thelovers.feature_auth.domain

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.ResponseDTO
import org.example.thelovers.core.domain.Result

interface AuthRepository {
    suspend fun sendPhoneNumber(
        phone: String
    ): Result<ResponseDTO<Boolean>, DataError.Remote>

    suspend fun validateSmsCode(
        phone: String,
        smsCode: String,
        deviceId: String
    ): Result<ResponseDTO<Map<String, String>>, DataError.Remote>
}