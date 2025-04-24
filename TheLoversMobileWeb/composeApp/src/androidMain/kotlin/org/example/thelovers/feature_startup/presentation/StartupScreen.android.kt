package org.example.thelovers.feature_startup.presentation

import androidx.compose.runtime.Composable
import org.example.thelovers.app.Route
import org.koin.compose.koinInject

@Composable
actual fun StartupScreen(
    onNavigate: (Route) -> Unit
) {
    val viewModel: StartupViewModel = koinInject<StartupViewModel>()

    StartupScreenContent(
        onNavigate = onNavigate,
        viewModel = viewModel
    )
}