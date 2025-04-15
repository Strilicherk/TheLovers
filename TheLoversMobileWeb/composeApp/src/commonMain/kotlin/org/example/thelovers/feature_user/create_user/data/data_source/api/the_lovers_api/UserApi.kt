package org.example.thelovers.feature_user.create_user.data.data_source.api.the_lovers_api

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.example.thelovers.core.data.safeCall
import org.example.thelovers.core.domain.DataError
import org.example.thelovers.feature_user.create_user.data.data_source.api.dto.user.CreateUserRequestDTO
import org.example.thelovers.core.domain.Result

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