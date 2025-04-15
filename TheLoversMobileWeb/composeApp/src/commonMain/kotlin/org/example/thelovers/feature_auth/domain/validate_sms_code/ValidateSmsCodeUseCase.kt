package org.example.thelovers.feature_auth.domain.validate_sms_code

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_auth.domain.AuthRepository
import org.example.thelovers.feature_auth.domain.DeviceIdProvider
import org.example.thelovers.feature_auth.domain.StorageTokensUseCase

class ValidateSmsCodeUseCase (
    private val repository: AuthRepository,
    private val useCase: StorageTokensUseCase
) {
    suspend operator fun invoke(phone: String, smsCode: String, deviceId: String): Result<Boolean, DataError.Remote> {
        return when(val result = repository.validateSmsCode(phone, smsCode, deviceId)) {
            is Result.Error -> {
                Result.Error(result.error)
            }
            is Result.Success -> {
                useCase.invoke(result.data.data)
                Result.Success(result.data.success)
            }
        }
    }
}