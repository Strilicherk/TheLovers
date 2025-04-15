package org.example.thelovers.feature_auth.presentation.send_phone_number

import org.example.thelovers.core.presentation.UiText

data class SendPhoneNumberState (
    val phoneNumber: String = "",
    val isPhoneNumberIncomplete: Boolean = false,
    val errorMessage: UiText? = null,
    val shouldNavigate: Boolean = false
)
