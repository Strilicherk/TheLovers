package org.example.thelovers.feature_validate_session.domain

import org.example.thelovers.app.Route
import org.example.thelovers.core.domain.Result
import org.example.thelovers.core.logger.Logger
import org.example.thelovers.core.secure_storage.SecureTokenStorage
import org.example.thelovers.feature_validate_session.data.ValidateSessionRequestDTO

class ValidateSessionUseCase(
    private val secureTokenStorage: SecureTokenStorage,
    private val validateSessionRepository: ValidateSessionRepository,
    private val logger: Logger
) {
    companion object {
        private const val TAG = "LOGS<ValidateSessionUseCase>"
    }

    suspend operator fun invoke(refreshToken: String, userId: String, deviceId: String): Route {
        logger.log(TAG, "Starting session validation. userId: $userId, deviceId: $deviceId")

        val apiResult = validateSessionRepository.validateSession(
            ValidateSessionRequestDTO(
                refreshToken, userId, deviceId
            )
        )

        return when (apiResult) {
            is Result.Error -> {
                logger.error(TAG, "Error validating session with the API.")
                Route.WelcomeScreen
            }

            is Result.Success -> {
                val dto = apiResult.data
                if (!dto.isRefreshTokenValid) {
                    logger.log(TAG, "Invalid refresh token. Redirecting to WelcomeScreen.")
                    Route.WelcomeScreen
                } else {
                    logger.log(TAG, "Session validated successfully. Saving new tokens.")
                    secureTokenStorage.clearTokens()
                    secureTokenStorage.saveTokens(
                        Tokens(dto.newRefreshToken!!, dto.newJwtToken!!)
                    )
                    Route.SwipeScreen
                }
            }
        }
    }
}