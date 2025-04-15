package org.example.thelovers.feature_auth.presentation.validate_sms_code

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import org.example.thelovers.R
import org.example.thelovers.feature_welcome.presentation.theme.WelcomeScreenBackgroundColor
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
actual fun ValidateSmsCodeScreen(phone: String) {
    val viewModel: ValidateSmsCodeViewModel =
        koinInject<ValidateSmsCodeViewModel>(parameters = { parametersOf(phone) })

    ValidateSmsCodeContent(
        titleFont = FontFamily(Font(R.font.angel_wish)),
        textFont = FontFamily(Font(R.font.inter)),
        backgroundColor = WelcomeScreenBackgroundColor,
        viewModel = viewModel
    )
}