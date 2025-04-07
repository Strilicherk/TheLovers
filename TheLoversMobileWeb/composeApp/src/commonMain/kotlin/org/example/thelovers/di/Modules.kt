package org.example.thelovers.di

import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect val plataformModule: Module

val sharedPlataform = module {
}
