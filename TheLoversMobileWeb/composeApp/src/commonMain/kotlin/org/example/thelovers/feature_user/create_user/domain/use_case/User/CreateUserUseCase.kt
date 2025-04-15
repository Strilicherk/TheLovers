package org.example.thelovers.feature_user.create_user.domain.use_case.User

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_user.create_user.domain.repository.UserRepository

class CreateUserUseCase (
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(phone: String): Result<Unit, DataError.Remote> {
        return userRepository.createUser(phone)
    }
}