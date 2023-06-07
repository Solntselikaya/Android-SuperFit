package com.example.superfit.data.network

import com.example.superfit.common.Constants
import com.example.superfit.domain.usecase.storage.token.GetTokenFromLocalStorageUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpProvider {

    companion object {
        fun provideClient(
            getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase
        ): OkHttpClient {
            val client = OkHttpClient.Builder().apply {
                connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                addInterceptor(Interceptor(getTokenFromLocalStorageUseCase))

                val logLevel = HttpLoggingInterceptor.Level.BODY
                addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
            }

            return client.build()
        }
    }
}