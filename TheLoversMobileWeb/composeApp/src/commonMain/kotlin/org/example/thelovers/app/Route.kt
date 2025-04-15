package org.example.thelovers.app

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object AppGraph : Route

    @Serializable
    data object WelcomeScreen : Route

    @Serializable
    data object SendPhoneNumberScreen : Route

    @Serializable
    data class ValidateSmsCodeScreen(val phone: String): Route
}