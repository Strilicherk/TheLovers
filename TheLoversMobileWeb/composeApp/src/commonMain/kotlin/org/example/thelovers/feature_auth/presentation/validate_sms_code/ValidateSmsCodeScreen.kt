package org.example.thelovers.feature_auth.presentation.validate_sms_code

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.thelovers.core.presentation.components.CustomButton
import org.jetbrains.compose.resources.stringResource
import theloversmobileweb.composeapp.generated.resources.Res
import theloversmobileweb.composeapp.generated.resources.button_next
import theloversmobileweb.composeapp.generated.resources.validate_sms_code_resend
import theloversmobileweb.composeapp.generated.resources.validate_sms_code_screen_title
import theloversmobileweb.composeapp.generated.resources.validate_sms_code_invalid_code

@Composable
expect fun ValidateSmsCodeScreen(phone: String)

@Composable
fun ValidateSmsCodeContent(
    titleFont: FontFamily,
    textFont: FontFamily,
    backgroundColor: Color,
    viewModel: ValidateSmsCodeViewModel
) {
    val state by viewModel.state.collectAsState()
    val code = remember { mutableStateOf("") }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.92f)
        ) {

            Text(
                text = stringResource(Res.string.validate_sms_code_screen_title),
                fontFamily = titleFont,
                color = White,
                fontSize = 50.sp,
                lineHeight = 50.sp,
            )

            Text(
                text = "11998098637",
                fontFamily = textFont,
                fontSize = 15.sp,
                color = White,
                modifier = Modifier
                    .padding(top = 10.dp)
            )

            OtpInput(
                code = state.smsCode,
                onCodeChange = { newValue ->
                    if (newValue.length <= 6 && newValue.all { it.isDigit() }) {
                        viewModel.onCodeChanged(newValue)
                    }
                }
            )

            Text(
                text = stringResource(Res.string.validate_sms_code_resend),
                fontFamily = textFont,
                fontSize = 15.sp,
                color = White,
                modifier = Modifier
                    .padding(top = 10.dp)
            )

            if (state.isSmsCodeIncomplete) {
                Text(
                    text = stringResource(Res.string.validate_sms_code_invalid_code),
                    fontFamily = textFont,
                    fontSize = 12.sp,
                    color = Red,
                    modifier = Modifier
                        .padding(top = 10.dp)
                )
            }


            CustomButton(
                onClick = {
                    if (state.smsCode.length == 6 && state.smsCode.all { it.isDigit() }) {
                        viewModel.onSubmitSmsCode(state.smsCode)
                    }
                },
                font = titleFont,
                text = stringResource(Res.string.button_next),
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(0.08f)
            )
        }
    }
}

@Composable
fun OtpInput(
    code: String,
    onCodeChange: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .padding(top = 50.dp)
            .clickable {
                focusRequester.requestFocus()
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(6) { index ->
                Column {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                    ) {
                        val char = code.getOrNull(index)?.toString() ?: ""
                        Text(
                            text = char,
                            style = TextStyle(color = White, fontSize = 24.sp),
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .width(50.dp)
                            .height(2.dp)
                            .background(White)
                    )
                }
            }
        }

        BasicTextField(
            value = code,
            onValueChange = {
                if (it.length <= 6) onCodeChange(it)
            },
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .focusRequester(focusRequester)
                .focusable(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            singleLine = true,
            cursorBrush = SolidColor(White),
        )
    }
}