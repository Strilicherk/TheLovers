package org.example.thelovers.feature_welcome.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
expect fun WelcomeScreen(onClick: () -> Unit)

@Composable
fun WelcomeScreenContentBase(
    imageMain: Painter,
    imageTitle: Painter,
    buttonFont: FontFamily,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.90f)
        ) {
            Image(
                painter = imageMain,
                contentDescription = "Vertical background image",
                contentScale = ContentScale.Fit
            )
            Image(
                painter = imageTitle,
                contentDescription = "The Lovers title",
                contentScale = ContentScale.Fit
            )
            OutlinedButton(
                onClick = onClick,
                shape = RectangleShape,
                content = {
                    Text(
                        text = "CadastraR/EntraR",
                        fontFamily = buttonFont,
                        color = White,
                        fontSize = 35.sp
                    )
                },
                modifier = Modifier
                    .padding(top = 100.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                colors = ButtonDefaults.buttonColors(Transparent),
                border = BorderStroke(1.dp, White)
            )
        }
    }
}