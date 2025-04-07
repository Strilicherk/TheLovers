package org.example.thelovers.feature_user.create_user.domain.repository

import org.example.thelovers.util.NetworkError
import org.example.thelovers.util.Result

interface UserRepository {
    suspend fun createUser(phone: String): Result<Unit, NetworkError>
}