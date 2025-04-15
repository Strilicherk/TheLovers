package org.example.thelovers.feature_auth.data

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.thelovers.core.data.safeCall
import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.ResponseDTO
import org.example.thelovers.core.domain.Result

private const val BASE_URL = "http://10.0.2.2:8080/api"

class AuthApi(
    private val httpClient: HttpClient
) {
    suspend fun sendPhoneNumber(
        phone: String
    ): Result<ResponseDTO<Boolean>, DataError.Remote> {
        return safeCall {
            httpClient.post("$BASE_URL/phone-verification/request") {
                contentType(ContentType.Application.Json)
                setBody(mapOf("phone" to phone))
            }
        }
    }

    suspend fun validateSmsCode(
        phone: String,
        smsCode: String,
        deviceId: String
    ): Result<ResponseDTO<Map<String, String>>, DataError.Remote> {
        return safeCall {
            httpClient.post("$BASE_URL/phone-verification/validate") {
                contentType(ContentType.Application.Json)
                setBody(
                    mapOf(
                        "phone" to phone,
                        "smsCode" to smsCode,
                        "deviceId" to deviceId
                    )
                )
            }
        }
    }
}