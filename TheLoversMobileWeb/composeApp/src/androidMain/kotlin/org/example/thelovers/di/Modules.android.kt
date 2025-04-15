package org.example.thelovers.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.example.thelovers.feature_auth.domain.AndroidDeviceIdProvider
import org.example.thelovers.feature_auth.domain.DeviceIdProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun provideHttpClientEngine(): HttpClientEngine = OkHttp.create()

val androidModule = module {
    single<DeviceIdProvider> { AndroidDeviceIdProvider(androidContext()) }
}