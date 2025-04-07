package org.example.thelovers.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Red,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(12.dp),

    text: String = "",
    font: FontFamily,
    fontSize: TextUnit = 20.sp,
    textColor: Color = Black,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(backgroundColor, roundedCornerShape)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = text,
            fontFamily = font,
            fontSize = fontSize,
            color = textColor
        )
    }
}