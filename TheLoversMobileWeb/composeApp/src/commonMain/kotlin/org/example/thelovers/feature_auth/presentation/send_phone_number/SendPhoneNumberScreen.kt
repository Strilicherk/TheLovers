package org.example.thelovers.feature_auth.presentation.send_phone_number

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.thelovers.core.domain.PhoneNumberMask
import org.example.thelovers.core.presentation.components.CustomButton
import org.example.thelovers.core.presentation.components.CustomTextField
import theloversmobileweb.composeapp.generated.resources.Res
import theloversmobileweb.composeapp.generated.resources.button_next
import theloversmobileweb.composeapp.generated.resources.error_invalid_phone_number
import theloversmobileweb.composeapp.generated.resources.send_phone_number_descriptive_text
import theloversmobileweb.composeapp.generated.resources.send_phone_number_hint
import theloversmobileweb.composeapp.generated.resources.send_phone_number_title
import org.jetbrains.compose.resources.stringResource

@Composable
expect fun SendPhoneNumberScreen(onNavigateToValidateCode: (String) -> Unit)

@Composable
fun SendPhoneNumberScreenContent(
    titleFont: FontFamily,
    textFont: FontFamily,
    backgroundColor: Color,
    viewModel: SendPhoneNumberViewModel,
    onNavigateToValidateCode: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.shouldNavigate) {
        if (state.shouldNavigate) {
            onNavigateToValidateCode(state.phoneNumber)
            viewModel.onNavigated()
        }
    }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.92f)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxHeight(0.15f),
                text = stringResource(Res.string.send_phone_number_title),
                fontFamily = titleFont,
                color = White,
                fontSize = 50.sp,
                lineHeight = 50.sp,
            )

            if (state.isPhoneNumberIncomplete || state.errorMessage != null) {
                Text(
                    text = state.errorMessage?.asString() ?: stringResource(Res.string.error_invalid_phone_number),
                    fontFamily = textFont,
                    fontSize = 12.sp,
                    color = Red,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            CustomTextField(
                value = state.phoneNumber,
                onValueChange = { newValue ->
                    if (newValue.length <= 11 && newValue.all { it.isDigit() }) {
                        viewModel.onPhoneNumberChanged(newValue)
                    }
                },
                font = textFont,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                keyBoardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                hint = stringResource(Res.string.send_phone_number_hint),
                visualTransformation = PhoneNumberMask()
            )

            Text(
                text = stringResource(Res.string.send_phone_number_descriptive_text),
                fontFamily = textFont,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth(),
                color = White
            )

            CustomButton(
                onClick = viewModel::onPhoneSubmit,
                font = titleFont,
                text = stringResource(Res.string.button_next),
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            )
        }
    }
}


