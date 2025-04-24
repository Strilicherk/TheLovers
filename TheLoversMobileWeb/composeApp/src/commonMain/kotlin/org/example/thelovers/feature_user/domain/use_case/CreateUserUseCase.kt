package org.example.thelovers.feature_user.domain.use_case

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_user.domain.repository.UserApiRepository

class CreateUserUseCase (
    private val userApiRepository: UserApiRepository
) {
    suspend operator fun invoke(phone: String): Result<Unit, DataError.Remote> {
        return userApiRepository.createUser(phone)
    }
}