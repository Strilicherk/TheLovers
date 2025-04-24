package org.example.thelovers.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import org.example.thelovers.feature_auth.data.AuthRepositoryImpl
import org.example.thelovers.feature_auth.presentation.send_phone_number.SendPhoneNumberViewModel
import org.example.thelovers.core.data.remote.HttpClientFactory
import org.example.thelovers.core.logger.Logger
import org.example.thelovers.core.logger.getLogger
import org.example.thelovers.feature_auth.data.AuthApi
import org.example.thelovers.feature_auth.domain.AuthRepository
import org.example.thelovers.feature_auth.domain.login.LoginUseCase
import org.example.thelovers.feature_auth.presentation.send_sms_code.SendSmsCodeViewModel
import org.example.thelovers.feature_profile.presentation.CreateProfileViewModel
import org.example.thelovers.feature_startup.presentation.StartupViewModel
import org.example.thelovers.feature_user.data.repository.UserApiRepositoryImpl
import org.example.thelovers.feature_user.data.repository.UserDaoRepositoryImpl
import org.example.thelovers.feature_user.domain.repository.UserApiRepository
import org.example.thelovers.feature_user.domain.repository.UserDaoRepository
import org.example.thelovers.feature_validate_session.data.ValidateSessionApi
import org.example.thelovers.feature_validate_session.data.ValidateSessionRepositoryImpl
import org.example.thelovers.feature_validate_session.domain.ValidateSessionRepository
import org.example.thelovers.feature_validate_session.domain.ValidateSessionUseCase
import org.koin.dsl.module

expect fun provideHttpClientEngine(): HttpClientEngine

val sharedModule = module {
    // Utils
    single<Logger> { getLogger() }

    // Remote
    single<HttpClientEngine> { provideHttpClientEngine() }
    single<HttpClient> { HttpClientFactory.create(get()) }
    single<AuthApi> { AuthApi(get()) }
    single<ValidateSessionApi> { ValidateSessionApi(get()) }

    // Local

    // Repositories
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<UserApiRepository> { UserApiRepositoryImpl(get()) }
    single<ValidateSessionRepository> { ValidateSessionRepositoryImpl(get(), get()) }
    single<UserDaoRepository> { UserDaoRepositoryImpl(get()) }

    // UseCase
    factory { ValidateSessionUseCase(get(), get(), get()) }
    factory { LoginUseCase(get(), get(), get(), get()) }

    // ViewModel
    single { StartupViewModel(get(), get(), get(), get(), get()) }
    factory { SendPhoneNumberViewModel(get()) }
    factory { (phone: String) -> SendSmsCodeViewModel(get(), get(), phone) }
    factory { CreateProfileViewModel() }
}


