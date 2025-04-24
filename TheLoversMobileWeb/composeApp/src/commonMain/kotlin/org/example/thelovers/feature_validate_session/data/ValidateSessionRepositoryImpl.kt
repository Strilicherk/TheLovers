package org.example.thelovers.feature_validate_session.data

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_validate_session.domain.ValidateSessionRepository
import org.example.thelovers.feature_validate_session.domain.Tokens
import org.example.thelovers.core.secure_storage.SecureTokenStorage

class ValidateSessionRepositoryImpl(
    private val api: ValidateSessionApi,
    private val storage: SecureTokenStorage
): ValidateSessionRepository {
    override suspend fun saveTokens(tokens: Tokens) {
        storage.saveTokens(tokens)
    }

    override suspend fun getSavedTokens(): Result<Tokens, DataError.Local> {
        return when(val result = storage.getTokens()) {
            is Result.Success -> {
                Result.Success(result.data)
            }
            is Result.Error -> {
                Result.Error(result.error)
            }
        }
    }

    override suspend fun validateSession(dto: ValidateSessionRequestDTO): Result<ValidateSessionResponseDTO, DataError.Remote> {
        try {
            return when (val response = api.validateTokens(dto)) {
                is Result.Success -> Result.Success(response.data.data)
                is Result.Error -> Result.Error(response.error)
            }
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }

    override suspend fun clearTokens() {
        storage.clearTokens()
    }
}