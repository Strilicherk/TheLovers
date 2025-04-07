package org.example.thelovers.feature_auth.insert_phone_number.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import org.example.thelovers.R
import org.example.thelovers.feature_welcome.presentation.theme.WelcomeScreenBackgroundColor

@Composable
actual fun InsertPhoneNumberScreen() {
    InsertPhoneNumberContent(
        titleFont = FontFamily(Font(R.font.angel_wish)),
        textFont = FontFamily(Font(R.font.inter)),
        backgroundColor = WelcomeScreenBackgroundColor,
    )
}