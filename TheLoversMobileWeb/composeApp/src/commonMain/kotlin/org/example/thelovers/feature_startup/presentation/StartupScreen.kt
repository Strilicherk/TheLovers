package org.example.thelovers.feature_startup.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.thelovers.app.Route

@Composable
expect fun StartupScreen(onNavigate: (Route) -> Unit)

@Composable
fun StartupScreenContent(
    onNavigate: (Route) -> Unit,
    viewModel: StartupViewModel
) {
    LaunchedEffect(Unit) {
        val destination = viewModel.decideInitialRoute()
        onNavigate(destination)
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}