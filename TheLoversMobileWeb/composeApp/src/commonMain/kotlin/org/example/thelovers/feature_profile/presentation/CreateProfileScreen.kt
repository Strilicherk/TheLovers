package org.example.thelovers.feature_profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
expect fun CreateProfileScreen()

@Composable
fun CreateProfileContent(
    titleFont: FontFamily,
    textFont: FontFamily,
    backgroundColor: Color,
    viewModel: CreateProfileViewModel,
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Create Profile Screen",
            fontSize = 24.sp,
            fontFamily = titleFont,
            color = Color.White
        )
    }
}