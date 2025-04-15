package org.example.thelovers.feature_auth.domain.send_phone_number

import org.example.thelovers.core.domain.DataError
import org.example.thelovers.core.domain.Result
import org.example.thelovers.feature_auth.domain.AuthRepository

class SendPhoneNumberUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(phone: String): Result<SendPhoneNumberResult, DataError.Remote> {
        val phoneWithDDI = "+55$phone"
        return when (val result = repository.sendPhoneNumber(phoneWithDDI)) {
            is Result.Success -> Result.Success(SendPhoneNumberResult.UserExists(result.data.data))
            is Result.Error -> Result.Error(result.error)
        }
    }
}