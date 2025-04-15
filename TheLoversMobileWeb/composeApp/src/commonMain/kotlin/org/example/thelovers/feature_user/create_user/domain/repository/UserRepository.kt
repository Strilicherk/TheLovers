package org.example.thelovers.feature_user.create_user.domain.repository

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result


interface UserRepository {
    suspend fun createUser(phone: String): Result<Unit, DataError.Remote>
}