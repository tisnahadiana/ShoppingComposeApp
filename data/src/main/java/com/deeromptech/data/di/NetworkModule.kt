package com.deeromptech.data.di

import android.util.Log
import com.deeromptech.data.network.NetworkServiceImpl
import com.deeromptech.domain.network.NetworkService
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import io.ktor.serialization.kotlinx.json.json

val networkModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("KoinBackendHandler : ", message)
                    }
                }
            }
        }
    }
    single<NetworkService> {
        NetworkServiceImpl(get())
    }
}