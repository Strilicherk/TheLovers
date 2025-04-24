package org.example.thelovers.feature_startup.presentation

import androidx.lifecycle.ViewModel
import org.example.thelovers.app.Route
import org.example.thelovers.core.domain.Result
import org.example.thelovers.core.logger.Logger
import org.example.thelovers.core.secure_storage.SecureTokenStorage
import org.example.thelovers.feature_auth.domain.DeviceIdProvider
import org.example.thelovers.feature_user.domain.repository.UserDaoRepository
import org.example.thelovers.feature_validate_session.domain.ValidateSessionUseCase

class StartupViewModel(
    private val sessionUseCase: ValidateSessionUseCase,
    private val userDaoRepository: UserDaoRepository,
    private val secureTokenStorage: SecureTokenStorage,
    private val deviceIdProvider: DeviceIdProvider,
    private val logger: Logger
) : ViewModel() {
    companion object {
        private const val TAG = "LOGS<StartupViewModel"
    }

    suspend fun decideInitialRoute(): Route {
        logger.log(TAG, "Starting initial route decision.")

        return when (val tokensResult = secureTokenStorage.getTokens()) {
            is Result.Error -> {
                logger.log(TAG, "No tokens found. Redirecting to WelcomeScreen.")
                Route.WelcomeScreen
            }
            is Result.Success -> {
                logger.log(TAG, "Tokens found successfully.")
                val user = userDaoRepository.getUser()
                if (user?.id == null) {
                    logger.log(TAG, "Local user not found. Redirecting to WelcomeScreen.")
                    Route.WelcomeScreen
                } else {
                    logger.log(TAG, "User found with ID: ${user.id}. Validating session.")
                    return sessionUseCase.invoke(
                        tokensResult.data.refreshToken,
                        user.id,
                        deviceIdProvider.getDeviceId()
                    )
                }
            }
        }
    }
}
