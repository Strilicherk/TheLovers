package org.example.thelovers.feature_auth.presentation.validate_sms_code

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.thelovers.core.domain.Result
import org.example.thelovers.core.presentation.UiText
import org.example.thelovers.feature_auth.domain.DeviceIdProvider
import org.example.thelovers.feature_auth.domain.validate_sms_code.SmsCode
import org.example.thelovers.feature_auth.domain.validate_sms_code.ValidateSmsCodeUseCase

class ValidateSmsCodeViewModel(
    private val useCase: ValidateSmsCodeUseCase,
    private val idProvider: DeviceIdProvider,
    private val phone: String,
) : ViewModel() {
    private val _state = MutableStateFlow(ValidateSmsCodeState())
    var state: StateFlow<ValidateSmsCodeState> = _state
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun onCodeChanged(newCode: String) {
        _state.update { it.copy(smsCode = newCode, isSmsCodeIncomplete = false) }
    }

    fun onSubmitSmsCode(smsCode: String) {
        println("VIEWMODEL: Phone at viewModel: $phone")
        viewModelScope.launch {
            val response = useCase.invoke(phone, smsCode, idProvider.getDeviceId())
            if (response != Result.Success(true)) {

            }
        }
    }
}