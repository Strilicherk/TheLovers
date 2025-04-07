package org.example.thelovers.feature_user.create_user.data.data_source.api.the_lovers_api

import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import org.example.thelovers.feature_user.create_user.data.data_source.api.dto.user.CreateUserRequestDTO
import org.example.thelovers.util.NetworkError
import org.example.thelovers.util.Result

class UserApi (
    private val httpClient: HttpClient
) {
    suspend fun createUser(createUserRequestDTO: CreateUserRequestDTO): Result<Unit,NetworkError> {
        return try {
            val response = httpClient.post(urlString = "") {
                contentType(ContentType.Application.Json)
                setBody(createUserRequestDTO)
            }
            if (response.status.isSuccess()) Result.Success(Unit) else Result.Error(NetworkError.SERVER_ERROR)
        } catch (e: UnresolvedAddressException) {
            return Result.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(NetworkError.SERIALIZATION)
        }
    }
}