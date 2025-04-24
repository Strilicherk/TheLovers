package org.example.thelovers.feature_profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import org.example.thelovers.R
import org.example.thelovers.feature_welcome.presentation.theme.WelcomeScreenBackgroundColor
import org.koin.compose.koinInject

@Composable
actual fun CreateProfileScreen() {
    val viewModel: CreateProfileViewModel = koinInject<CreateProfileViewModel>()

    CreateProfileContent(
        titleFont = FontFamily(Font(R.font.angel_wish)),
        textFont = FontFamily(Font(R.font.inter)),
        backgroundColor = WelcomeScreenBackgroundColor,
        viewModel = viewModel,
    )
}