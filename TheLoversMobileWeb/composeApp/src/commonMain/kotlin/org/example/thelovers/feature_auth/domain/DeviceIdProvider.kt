package org.example.thelovers.feature_auth.domain

interface DeviceIdProvider {
    fun getDeviceId(): String
}