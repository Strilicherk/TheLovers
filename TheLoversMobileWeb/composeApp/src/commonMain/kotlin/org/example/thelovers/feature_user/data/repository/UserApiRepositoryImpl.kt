package org.example.thelovers.feature_user.data.repository

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_user.data.data_source.UserApi
import org.example.thelovers.feature_user.data.dto.CreateUserRequestDTO
import org.example.thelovers.feature_user.domain.repository.UserApiRepository

class UserApiRepositoryImpl(
    private val userApi: UserApi
) : UserApiRepository {
    override suspend fun createUser(phone: String): Result<Unit, DataError.Remote> {
        return userApi.createUser(
            CreateUserRequestDTO(
                phone
            )
        )
    }
}