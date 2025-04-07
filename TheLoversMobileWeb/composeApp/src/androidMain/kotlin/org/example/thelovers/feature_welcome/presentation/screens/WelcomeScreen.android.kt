package org.example.thelovers.feature_welcome.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import org.example.thelovers.R
import org.example.thelovers.feature_welcome.presentation.theme.WelcomeScreenBackgroundColor

@Composable
actual fun WelcomeScreen(onClick: () -> Unit) {
    WelcomeScreenContentBase(
        imageMain = painterResource(R.drawable.thelovers795),
        imageTitle = painterResource(R.drawable.thelovers_title),
        buttonFont = FontFamily(Font(R.font.angel_wish)),
        backgroundColor = WelcomeScreenBackgroundColor,
        onClick = onClick
    )
}