package org.example.thelovers.feature_auth.data

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.thelovers.core.data.remote.safeCall
import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.ResponseDTO
import org.example.thelovers.core.domain.Result

private const val BASE_URL = "http://10.0.2.2:8080/auth"

class AuthApi(
    private val httpClient: HttpClient
) {
    suspend fun sendPhoneNumber(
        phone: String
    ): Result<ResponseDTO<Boolean>, DataError.Remote> {
        return safeCall {
            httpClient.post("$BASE_URL/request-code") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("phone" to phone))
            }
        }
    }

    suspend fun sendSmsCode(
        phone: String,
        smsCode: String,
        deviceId: String
    ): Result<ResponseDTO<LoginResponseDTO>, DataError.Remote> {
        return safeCall {
            httpClient.post("$BASE_URL/verify-code") {
                contentType(ContentType.Application.Json)
                setBody(LoginRequestDTO(phone, smsCode, deviceId))
            }
        }
    }
}