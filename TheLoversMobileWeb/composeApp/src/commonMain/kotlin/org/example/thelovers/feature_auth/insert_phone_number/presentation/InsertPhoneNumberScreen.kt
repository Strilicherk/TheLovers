package org.example.thelovers.feature_auth.insert_phone_number.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.thelovers.core.domain.PhoneNumberMask
import org.example.thelovers.core.presentation.components.CustomButton
import org.example.thelovers.core.presentation.components.CustomTextField

@Composable
expect fun InsertPhoneNumberScreen()

@Composable
fun InsertPhoneNumberContent(
    titleFont: FontFamily,
    textFont: FontFamily,
    backgroundColor: Color,
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.9f)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxHeight(0.15f),
                text = "Posso pegar seu número?",
                fontFamily = titleFont,
                color = White,
                fontSize = 50.sp,
                lineHeight = 50.sp,
            )

            var phoneNumber by remember { mutableStateOf("") }

            CustomTextField(
                value = phoneNumber,
                onValueChange = { newValue ->
                    if (newValue.length < 12) {
                        phoneNumber = newValue.filter { it.isDigit() }
                    }
                },
                font = textFont,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                hint = "(11) 66666-6666",
                visualTransformation = PhoneNumberMask()
            )

            Text(
                text = "Iremos te enviar um código para validar seu número de telefone. Não se preocupe, você poderá alterar esse número posteriormente.",
                fontFamily = textFont,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                color = White
            )

            CustomButton(
                onClick = {},
                font = titleFont,
                text = "Próximo",
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            )
        }
    }
}
