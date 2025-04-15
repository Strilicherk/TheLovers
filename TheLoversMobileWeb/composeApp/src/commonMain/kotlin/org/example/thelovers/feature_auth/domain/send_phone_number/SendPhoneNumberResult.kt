package org.example.thelovers.feature_auth.domain.send_phone_number

sealed class SendPhoneNumberResult {
    data class UserExists(val exists: Boolean): SendPhoneNumberResult()
}