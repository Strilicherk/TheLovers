package org.example.thelovers.feature_auth.presentation.send_sms_code

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import org.example.thelovers.R
import org.example.thelovers.app.Route
import org.example.thelovers.feature_welcome.presentation.theme.WelcomeScreenBackgroundColor
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
actual fun SendSmsCodeScreen(
    phone: String,
    onNavigate: (Route) -> Unit
) {
    val viewModel: SendSmsCodeViewModel =
        koinInject<SendSmsCodeViewModel>(parameters = { parametersOf(phone) })

    SendSmsCodeContent(
        titleFont = FontFamily(Font(R.font.angel_wish)),
        textFont = FontFamily(Font(R.font.inter)),
        backgroundColor = WelcomeScreenBackgroundColor,
        viewModel = viewModel,
        onNavigate = onNavigate
    )
}
