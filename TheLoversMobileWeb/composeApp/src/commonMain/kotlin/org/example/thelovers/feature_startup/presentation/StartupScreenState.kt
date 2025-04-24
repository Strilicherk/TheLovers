package org.example.thelovers.feature_startup.presentation

import org.example.thelovers.app.Route

data class StartupScreenState(
    val shouldNavigate: Boolean = true,
    val destination: Route? = null
)