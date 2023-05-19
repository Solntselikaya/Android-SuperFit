package com.example.superfit.data.network

import android.content.Context
import com.example.superfit.domain.usecase.storage.token.GetTokenFromLocalStorageUseCase
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class Interceptor(
    private val getTokenFromLocalStorageUseCase: GetTokenFromLocalStorageUseCase
): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        //val getToken = GetTokenFromLocalStorageUseCase()
        val accessToken = getTokenFromLocalStorageUseCase.execute()

        val request: Request = chain.request()
        val newRequest: Request = if (accessToken.access_token.isNotBlank()) {
            newRequestWithAccessToken(request, accessToken.access_token)
        } else {
            request.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build()
        }

        val response = chain.proceed(newRequest)
        if (response.code == 400) {
            throw IOException("HTTP 400")
        }
        return response
    }

    private fun newRequestWithAccessToken(
        request: Request,
        accessToken: String
    ): Request {
        return request.newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer $accessToken")
            .build()
    }

}