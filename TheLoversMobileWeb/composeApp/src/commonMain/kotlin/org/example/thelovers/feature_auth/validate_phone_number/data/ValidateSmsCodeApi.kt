package org.example.thelovers.feature_auth.insert_phone_number.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.example.thelovers.util.NetworkError
import org.example.thelovers.util.Result

class ValidateSmsCodeApi(
    private val httpClient: HttpClient
) {
    suspend fun validateSmsCode(
        phone: String,
        code: String,
        deviceId: String
    ): Result<Map<String,String>, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = ""
            ) {
                parameter("phone", phone)
                parameter("code", code)
                parameter("deviceId", deviceId)
            }
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }

        return when(response.status.value) {
            in  200..299 -> {
                val tokens = response.body<Map<String, String>>()
                Result.Success(tokens)
            }
            401 -> Result.Error(NetworkError.UNAUTHORIZED)
            409 -> Result.Error(NetworkError.CONFLICT)
            408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
            413 -> Result.Error(NetworkError.PAYLOAD_TOO_LARGE)
            in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
            else -> Result.Error(NetworkError.UNKNOWN)
        }
    }
}