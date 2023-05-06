package com.example.superfit.di

import com.example.superfit.common.Constants.BASE_URL
import com.example.superfit.common.Constants.CONNECT_TIMEOUT
import com.example.superfit.common.Constants.READ_TIMEOUT
import com.example.superfit.common.Constants.WRITE_TIMEOUT
import com.example.superfit.data.Interceptor
import com.example.superfit.data.remote.ProfileApi
import com.example.superfit.data.repository.ProfileRepositoryImpl
import com.example.superfit.domain.repository.ProfileRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModule = module {

    val client = OkHttpClient.Builder().apply {
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(Interceptor())

        val logLevel = HttpLoggingInterceptor.Level.BODY
        addInterceptor(HttpLoggingInterceptor().setLevel(logLevel))
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(ProfileApi::class.java)
    }

    single<ProfileRepository> {
        ProfileRepositoryImpl(get())
    }

}