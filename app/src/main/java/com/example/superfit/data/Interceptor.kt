package com.example.superfit.data

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class Interceptor: Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val newRequest: Request = request.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            //.addHeader("Authorization", "Bearer ${token}")
            .build()
        return chain.proceed(newRequest)
    }

}