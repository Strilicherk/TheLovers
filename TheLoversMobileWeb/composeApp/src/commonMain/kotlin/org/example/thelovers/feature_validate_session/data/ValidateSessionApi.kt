package org.example.thelovers.feature_validate_session.data

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

class ValidateSessionApi(
    private val httpClient: HttpClient
) {
    suspend fun validateTokens(dto: ValidateSessionRequestDTO): Result<ResponseDTO<ValidateSessionResponseDTO>, DataError.Remote> {
        return safeCall {
            httpClient.post("$BASE_URL/validate-session") {
                contentType(ContentType.Application.Json)
                setBody(dto)
            }
        }
    }
}