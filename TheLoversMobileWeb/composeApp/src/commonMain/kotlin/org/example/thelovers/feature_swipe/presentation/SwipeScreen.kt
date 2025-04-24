package org.example.thelovers.feature_swipe.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable
expect fun SwipeScreen()

@Composable
fun SwipeScreenContent(
    titleFont: FontFamily,
    textFont: FontFamily,
    backgroundColor: Color,
    viewModel: SwipeScreenViewModel,
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Swipe Screen",
            fontSize = 24.sp,
            fontFamily = titleFont,
            color = Color.White
        )
    }
}
