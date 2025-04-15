package org.example.thelovers.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.example.thelovers.feature_auth.data.AuthRepositoryImpl
import org.example.thelovers.feature_auth.domain.send_phone_number.SendPhoneNumberUseCase
import org.example.thelovers.feature_auth.presentation.send_phone_number.SendPhoneNumberViewModel
import org.example.thelovers.core.data.HttpClientFactory
import org.example.thelovers.feature_auth.data.AuthApi
import org.example.thelovers.feature_auth.domain.AuthRepository
import org.example.thelovers.feature_auth.domain.DeviceIdProvider
import org.example.thelovers.feature_auth.domain.StorageTokensUseCase
import org.example.thelovers.feature_auth.domain.validate_sms_code.ValidateSmsCodeUseCase
import org.example.thelovers.feature_auth.presentation.validate_sms_code.ValidateSmsCodeViewModel
import org.koin.dsl.module

expect fun provideHttpClientEngine(): HttpClientEngine

val sharedModule = module {
    // core
    single<HttpClientEngine> { provideHttpClientEngine() }
    single<HttpClient> { HttpClientFactory.create(get()) }

    // feature_auth
    single<AuthApi> { AuthApi(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get())}

    factory { SendPhoneNumberUseCase(get()) }
    factory { SendPhoneNumberViewModel(get()) }
    factory { ValidateSmsCodeUseCase(get(), get())}
    factory { StorageTokensUseCase() }
    factory { (phone: String) -> ValidateSmsCodeViewModel(get(), get(), phone) }
}


