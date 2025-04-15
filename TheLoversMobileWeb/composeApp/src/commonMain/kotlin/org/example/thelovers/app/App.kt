package org.example.thelovers.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.example.thelovers.feature_auth.presentation.send_phone_number.SendPhoneNumberScreen
import org.example.thelovers.feature_auth.presentation.validate_sms_code.ValidateSmsCodeScreen
import org.example.thelovers.feature_welcome.presentation.screens.WelcomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.AppGraph
        ) {
            navigation<Route.AppGraph>(
                startDestination = Route.WelcomeScreen
            ) {
                composable<Route.WelcomeScreen> {
                    WelcomeScreen(onClick = {navController.navigate(Route.SendPhoneNumberScreen)})
                }
                composable<Route.SendPhoneNumberScreen> {
                    SendPhoneNumberScreen { phone ->
                        navController.navigate(Route.ValidateSmsCodeScreen(phone))
                    }
                }
                composable<Route.ValidateSmsCodeScreen> { entry ->
                    val args = entry.toRoute<Route.ValidateSmsCodeScreen>()
                    ValidateSmsCodeScreen(args.phone)
                }
            }
        }
    }
}