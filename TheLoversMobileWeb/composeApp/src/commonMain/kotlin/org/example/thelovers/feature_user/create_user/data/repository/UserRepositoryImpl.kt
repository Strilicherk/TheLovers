package org.example.thelovers.feature_user.create_user.data.repository

import org.example.thelovers.feature_user.create_user.data.data_source.api.dto.user.CreateUserRequestDTO
import org.example.thelovers.feature_user.create_user.data.data_source.api.the_lovers_api.UserApi
import org.example.thelovers.feature_user.create_user.domain.repository.UserRepository
import org.example.thelovers.util.NetworkError
import org.example.thelovers.util.Result

class UserRepositoryImpl(
    private val userApi: UserApi
) : UserRepository {
    override suspend fun createUser(phone: String): Result<Unit, NetworkError> {
        return userApi.createUser(CreateUserRequestDTO(phone))
    }
}