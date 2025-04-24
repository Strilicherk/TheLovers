package org.example.thelovers.feature_auth.domain

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings

class AndroidDeviceIdProvider(private val context: Context) : DeviceIdProvider {
    @SuppressLint("HardwareIds")
    override fun getDeviceId(): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }
}