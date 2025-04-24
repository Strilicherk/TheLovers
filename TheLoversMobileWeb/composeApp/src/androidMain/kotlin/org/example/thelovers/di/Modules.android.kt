package org.example.thelovers.di

import androidx.room.Room
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.example.thelovers.core.data.local.AppDatabase
import org.example.thelovers.feature_auth.data.AndroidSecureStorage
import org.example.thelovers.core.secure_storage.SecureTokenStorage
import org.example.thelovers.feature_auth.domain.AndroidDeviceIdProvider
import org.example.thelovers.feature_auth.domain.DeviceIdProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun provideHttpClientEngine(): HttpClientEngine = OkHttp.create()

val androidModule = module {
    single<DeviceIdProvider> { AndroidDeviceIdProvider(androidContext()) }
    single<SecureTokenStorage> { AndroidSecureStorage(get()) }

    single { Room.databaseBuilder(get(), AppDatabase::class.java, "thelovers_db").fallbackToDestructiveMigration(true).build() }
    single { get<AppDatabase>().userDao() }
}