package com.example.superfit.data.network

import android.content.Context
import com.example.superfit.common.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

class OkHttpProvider {

    companion object {
        fun provideClient(context: Context): OkHttpClient {
            val client = OkHttpClient.Builder().apply {
                connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                addInterceptor(Interceptor(context))

                val logLevel = HttpLoggingInterceptor.Level.BODY
                addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
            }

            return client.build()
        }
    }
}