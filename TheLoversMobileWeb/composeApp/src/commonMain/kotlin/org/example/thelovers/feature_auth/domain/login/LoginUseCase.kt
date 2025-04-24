package org.example.thelovers.feature_auth.domain.login

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.core.domain.map
import org.example.thelovers.core.domain.onError
import org.example.thelovers.core.logger.Logger
import org.example.thelovers.feature_auth.domain.AuthRepository
import org.example.thelovers.feature_validate_session.domain.Tokens
import org.example.thelovers.core.secure_storage.SecureTokenStorage
import org.example.thelovers.feature_user.data.mapper.UserMapper.toUserEntity
import org.example.thelovers.feature_user.domain.repository.UserDaoRepository

class LoginUseCase(
    private val repository: AuthRepository,
    private val secureTokenStorage: SecureTokenStorage,
    private val userDaoRepository: UserDaoRepository,
    private val logger: Logger
) {
    suspend fun login(
        phone: String,
        smsCode: String,
        deviceId: String
    ): Result<Boolean, DataError> {
        val TAG = "LOGS<LoginUseCase>"
        logger.log(TAG, "Attempting login with phone: +55$phone, deviceId: $deviceId")

        return repository.sendSmsCode("+55$phone", smsCode, deviceId).map { loginResponseDTO ->
            logger.log(TAG, "SMS code verified. Received tokens and user data.")

            secureTokenStorage.saveTokens(
                Tokens(
                    refreshToken = loginResponseDTO.data.refreshToken,
                    jwtToken = loginResponseDTO.data.jwtToken
                )
            )
            logger.log(TAG, "Tokens saved to secure storage.")

            val userEntity = loginResponseDTO.data.user.toUserEntity()
            userDaoRepository.upsertUser(userEntity)
            logger.log(TAG, "User saved to local database: ${userEntity.id}")

            val isUserComplete = !loginResponseDTO.data.user.isIncomplete()
            logger.log(TAG, "User completeness check: $isUserComplete")

            isUserComplete
        }.onError {
            logger.error(TAG, "Login failed.")
            Result.Error(it)
        }
    }
}