package org.example.thelovers.feature_auth.presentation.send_phone_number

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import org.example.thelovers.R
import org.example.thelovers.feature_auth.presentation.send_phone_number.SendPhoneNumberScreenContent
import org.example.thelovers.feature_auth.presentation.send_phone_number.SendPhoneNumberViewModel
import org.example.thelovers.feature_welcome.presentation.theme.WelcomeScreenBackgroundColor
import org.koin.compose.koinInject

@Composable
actual fun SendPhoneNumberScreen(onNavigateToValidateCode: (String) -> Unit) {
    val viewModel: SendPhoneNumberViewModel = koinInject<SendPhoneNumberViewModel>()

    SendPhoneNumberScreenContent(
        titleFont = FontFamily(Font(R.font.angel_wish)),
        textFont = FontFamily(Font(R.font.inter)),
        backgroundColor = WelcomeScreenBackgroundColor,
        viewModel = viewModel,
        onNavigateToValidateCode = onNavigateToValidateCode
    )
}
