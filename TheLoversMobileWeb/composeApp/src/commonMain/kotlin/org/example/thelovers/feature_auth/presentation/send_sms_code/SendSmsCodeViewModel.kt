package org.example.thelovers.feature_auth.presentation.send_sms_code

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.thelovers.app.Route
import org.example.thelovers.core.domain.Result
import org.example.thelovers.core.presentation.toUiText
import org.example.thelovers.feature_auth.domain.DeviceIdProvider
import org.example.thelovers.feature_auth.domain.login.LoginUseCase

class SendSmsCodeViewModel(
    private val loginUseCase: LoginUseCase,
    private val idProvider: DeviceIdProvider,
    private val phone: String,
) : ViewModel() {
    private val _state = MutableStateFlow(SendSmsCodeState())
    var state: StateFlow<SendSmsCodeState> = _state
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun onCodeChanged(newCode: String) {
        _state.update {
            it.copy(
                smsCode = newCode,
                isSmsCodeIncomplete = false,
                errorMessage = null
            )
        }
    }

    fun onSubmitSmsCode(smsCode: String) {
        viewModelScope.launch {
            when (val result = loginUseCase.login(phone, smsCode, idProvider.getDeviceId())) {
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            navigateTo = if (result.data) Route.SwipeScreen else Route.WelcomeScreen
                        )
                    }
                }

                is Result.Error -> {
                    _state.update {
                        it.copy(errorMessage = result.error.toUiText())
                    }
                }
            }
        }
    }

    fun onNavigated() {
        _state.update { it.copy(navigateTo = null) }
    }
}