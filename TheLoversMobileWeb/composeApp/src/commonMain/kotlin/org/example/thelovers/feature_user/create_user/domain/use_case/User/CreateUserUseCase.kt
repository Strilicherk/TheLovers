package org.example.thelovers.feature_user.create_user.domain.use_case.User

import org.example.thelovers.feature_user.create_user.domain.repository.UserRepository
import org.example.thelovers.util.NetworkError
import org.example.thelovers.util.Result

class CreateUserUseCase (
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(phone: String): Result<Unit, NetworkError> {
        return userRepository.createUser(phone)
    }
}