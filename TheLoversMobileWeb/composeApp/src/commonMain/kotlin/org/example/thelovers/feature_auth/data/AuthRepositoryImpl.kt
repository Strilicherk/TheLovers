package org.example.thelovers.feature_auth.data

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.ResponseDTO
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_auth.domain.AuthRepository

class AuthRepositoryImpl(
    private val dataSource: AuthApi
) : AuthRepository {
    override suspend fun sendPhoneNumber(phone: String): Result<ResponseDTO<Boolean>, DataError.Remote> {
        return dataSource.sendPhoneNumber(phone)
    }

    override suspend fun sendSmsCode(
        phone: String,
        smsCode: String,
        deviceId: String
    ): Result<ResponseDTO<LoginResponseDTO>, DataError.Remote> {
        return dataSource.sendSmsCode(phone, smsCode, deviceId)
    }
}