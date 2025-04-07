package org.example.thelovers.core.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    textColor: Color = Color.White,
    font: FontFamily,
    fontSize: TextUnit = 20.sp,
    keyBoardOptions: KeyboardOptions = KeyboardOptions.Default,
    cursorColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(12.dp),
    borderColor: Color = Color.White,
    hint: String = "",
    hintColor: Color = Color.Gray,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        textStyle = TextStyle(
            color = textColor,
            fontFamily = font,
            fontSize = fontSize
        ),
        keyboardOptions = keyBoardOptions,
        cursorBrush = SolidColor(cursorColor),
        visualTransformation = visualTransformation,
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .border(1.dp, borderColor, shape)
                    .padding(horizontal = 16.dp, vertical = 12.dp) // padding interno
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        color = hintColor,
                        fontFamily = font,
                        fontSize = fontSize
                    )
                }
                innerTextField()
            }
        }
    )
}