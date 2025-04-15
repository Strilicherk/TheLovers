package org.example.thelovers.feature_auth.presentation.validate_sms_code

import org.example.thelovers.core.presentation.UiText

data class ValidateSmsCodeState (
    val smsCode: String = "",
    val isSmsCodeIncomplete: Boolean = false,
    val errorMessage: UiText? = null,
    val shouldNavigate: Boolean = false
)

