package org.example.thelovers.feature_auth.presentation.send_sms_code

import org.example.thelovers.app.Route
import org.example.thelovers.core.presentation.UiText

data class SendSmsCodeState (
    val smsCode: String = "",
    val isSmsCodeIncomplete: Boolean = false,
    val errorMessage: UiText? = null,
    val navigateTo: Route? = null,
)

