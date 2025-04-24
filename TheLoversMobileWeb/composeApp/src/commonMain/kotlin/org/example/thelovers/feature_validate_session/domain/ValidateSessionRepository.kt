package org.example.thelovers.feature_validate_session.domain

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_validate_session.data.ValidateSessionRequestDTO
import org.example.thelovers.feature_validate_session.data.ValidateSessionResponseDTO

interface ValidateSessionRepository {
    suspend fun validateSession(dto: ValidateSessionRequestDTO): Result<ValidateSessionResponseDTO, DataError.Remote>
    suspend fun saveTokens(tokens: Tokens)
    suspend fun getSavedTokens(): Result<Tokens, DataError.Local>
    suspend fun clearTokens()
}