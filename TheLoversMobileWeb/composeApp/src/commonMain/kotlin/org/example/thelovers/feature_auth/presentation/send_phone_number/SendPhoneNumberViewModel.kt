package org.example.thelovers.feature_auth.presentation.send_phone_number

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.thelovers.core.domain.Result
import org.example.thelovers.core.presentation.toUiText
import org.example.thelovers.feature_auth.domain.AuthRepository

class SendPhoneNumberViewModel(
    private val repository: AuthRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SendPhoneNumberState())
    var state: StateFlow<SendPhoneNumberState> = _state

    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun onPhoneNumberChanged(newPhone: String) {
        _state.update { it.copy(phoneNumber = newPhone) }
    }

    fun onPhoneSubmit() {
        val phone = _state.value.phoneNumber
        when {
            phone.length < 11 -> {
                _state.update {
                    it.copy(
                        isPhoneNumberIncomplete = true,
                    )
                }
                return
            } else -> {
                _state.update {
                    it.copy(
                        isPhoneNumberIncomplete = false,
                    )
                }

                viewModelScope.launch {
                    when (val result = repository.sendPhoneNumber(phone)) {
                        is Result.Success -> {
                            _state.update { it.copy(shouldNavigate = true) }
                        }

                        is Result.Error -> _state.update {
                            it.copy(
                                errorMessage = result.error.toUiText()
                            )
                        }
                    }
                }

            }
        }
    }

    fun onNavigated() {
        _state.update { it.copy(shouldNavigate = false) }
    }
}