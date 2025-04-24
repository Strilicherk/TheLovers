package org.example.thelovers.app

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.example.thelovers.core.logger.Logger
import org.example.thelovers.feature_auth.presentation.send_phone_number.SendPhoneNumberScreen
import org.example.thelovers.feature_auth.presentation.send_sms_code.SendSmsCodeScreen
import org.example.thelovers.feature_profile.presentation.CreateProfileScreen
import org.example.thelovers.feature_startup.presentation.StartupScreen
import org.example.thelovers.feature_swipe.presentation.SwipeScreen
import org.example.thelovers.feature_welcome.presentation.screens.WelcomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    logger: Logger
) {
    val TAG = "LOGS<App>"
    logger.log(TAG, "App Composable started.")

    MaterialTheme {
        val navController = rememberNavController()
        logger.log(TAG, "Navigation controller initialized.")

        NavHost(
            navController = navController,
            startDestination = Route.AppGraph
        ) {
            navigation<Route.AppGraph>(
                startDestination = Route.StartupScreen
            ) {
                composable<Route.StartupScreen> {
                    logger.log(TAG, "Navigated to StartupScreen.")
                    StartupScreen(
                        onNavigate = { route ->
                            logger.log(TAG, "Navigating from StartupScreen to route: $route")
                            navController.navigate(route) {
                                popUpTo(Route.StartupScreen) { inclusive = true }
                                launchSingleTop = true
                            }
                        }
                    )
                }

                composable<Route.WelcomeScreen> {
                    logger.log(TAG, "Navigated to WelcomeScreen.")
                    WelcomeScreen(onClick = {
                        logger.log(TAG, "Navigating to SendPhoneNumberScreen from WelcomeScreen.")
                        navController.navigate(Route.SendPhoneNumberScreen)
                    })
                }

                composable<Route.SendPhoneNumberScreen> {
                    logger.log(TAG, "Navigated to SendPhoneNumberScreen.")
                    SendPhoneNumberScreen { phone ->
                        logger.log(TAG, "Phone number submitted: $phone. Navigating to ValidateSmsCodeScreen.")
                        navController.navigate(
                            Route.ValidateSmsCodeScreen(phone)
                        )
                    }
                }

                composable<Route.ValidateSmsCodeScreen> { entry ->
                    val args = entry.toRoute<Route.ValidateSmsCodeScreen>()
                    logger.log(TAG, "Navigated to ValidateSmsCodeScreen with phone: ${args.phone}.")
                    SendSmsCodeScreen(
                        args.phone,
                        onNavigate = { route ->
                            logger.log(TAG, "Navigating from ValidateSmsCodeScreen to route: $route")
                            navController.navigate(route) {
                                popUpTo(Route.StartupScreen) { inclusive = true }
                            }
                        }
                    )
                }

                composable<Route.CreateProfileScreen> {
                    logger.log(TAG, "Navigated to CreateProfileScreen.")
                    CreateProfileScreen()
                }

                composable<Route.SwipeScreen> {
                    logger.log(TAG, "Navigated to SwipeScreen.")
                    SwipeScreen()
                }
            }
        }
    }
}
