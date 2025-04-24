package org.example.thelovers.core.secure_storage

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_validate_session.domain.Tokens

interface SecureTokenStorage {
    suspend fun saveTokens(tokens: Tokens): Result<Unit, DataError.Local>
    suspend fun getTokens(): Result<Tokens, DataError.Local>
    suspend fun clearTokens(): Result<Unit, DataError.Local>
}