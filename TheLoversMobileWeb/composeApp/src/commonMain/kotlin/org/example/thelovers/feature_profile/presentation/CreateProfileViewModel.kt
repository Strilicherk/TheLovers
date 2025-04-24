package org.example.thelovers.feature_profile.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateProfileViewModel (

): ViewModel() {
    private val _state = MutableStateFlow(CreateProfileState())
    val state: StateFlow<CreateProfileState> = _state
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
}