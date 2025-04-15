package org.example.thelovers

import android.os.Bundle
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import org.example.thelovers.app.App
import org.example.thelovers.di.androidModule
import org.example.thelovers.di.sharedModule
import org.example.thelovers.feature_auth.presentation.validate_sms_code.ValidateSmsCodeScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startKoin {
            androidContext(this@MainActivity) // se for em MainActivity
            modules(sharedModule, androidModule)
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = android.graphics.Color.TRANSPARENT
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        setContent {
//            ValidateSmsCodeScreen("+5511998098637")
            App()
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppAndroidPreview() {
    App()
}