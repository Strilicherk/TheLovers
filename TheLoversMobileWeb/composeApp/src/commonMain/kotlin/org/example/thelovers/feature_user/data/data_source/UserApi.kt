package org.example.thelovers.feature_user.data.data_source

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.thelovers.core.data.remote.safeCall
import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_user.data.dto.CreateUserRequestDTO

class UserApi(
    private val httpClient: HttpClient
) {
    suspend fun createUser(
        createUserRequestDTO: CreateUserRequestDTO
    ): Result<Unit, DataError.Remote> {
        return safeCall {
            httpClient.post(urlString = "") {
                contentType(ContentType.Application.Json)
                setBody(createUserRequestDTO)
            }
        }
    }
}