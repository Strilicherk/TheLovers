package org.example.thelovers.feature_swipe.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SwipeScreenViewModel(

): ViewModel() {
    private val _state = MutableStateFlow(SwipeScreenState())
    var state: StateFlow<SwipeScreenState> = _state
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)


}