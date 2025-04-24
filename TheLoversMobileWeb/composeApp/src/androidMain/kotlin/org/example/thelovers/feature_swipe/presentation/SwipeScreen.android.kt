package org.example.thelovers.feature_swipe.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import org.example.thelovers.R
import org.example.thelovers.feature_welcome.presentation.theme.WelcomeScreenBackgroundColor
import org.koin.compose.koinInject

@Composable
actual fun SwipeScreen() {
    val viewModel: SwipeScreenViewModel = koinInject<SwipeScreenViewModel>()

    SwipeScreenContent(
        titleFont = FontFamily(Font(R.font.angel_wish)),
        textFont = FontFamily(Font(R.font.inter)),
        backgroundColor = WelcomeScreenBackgroundColor,
        viewModel = viewModel,
    )
}