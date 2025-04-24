package org.example.thelovers.feature_user.domain.repository

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result

interface UserApiRepository {
    suspend fun createUser(phone: String): Result<Unit, DataError.Remote>
}