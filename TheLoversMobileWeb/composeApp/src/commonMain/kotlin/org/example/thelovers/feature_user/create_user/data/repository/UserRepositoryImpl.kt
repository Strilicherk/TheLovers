package org.example.thelovers.feature_user.create_user.data.repository

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_user.create_user.data.data_source.api.dto.user.CreateUserRequestDTO
import org.example.thelovers.feature_user.create_user.data.data_source.api.the_lovers_api.UserApi
import org.example.thelovers.feature_user.create_user.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun createUser(phone: String): Result<Unit, DataError.Remote> {
        return userApi.createUser(CreateUserRequestDTO(phone))
    }
}