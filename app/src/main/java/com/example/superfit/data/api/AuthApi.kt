package com.example.superfit.data.api

import com.example.superfit.data.dto.*
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {

    @POST("auth/token")
    suspend fun login(
        @Body body: AuthCredntialsDto
    ): AuthResponceDto

    @POST("auth/token/refresh")
    suspend fun getToken(
        @Body body: RefreshTokenDto
    ): AcessTokenDto

    @POST("auth/register")
    suspend fun register(
        @Body body: RegistartionDto
    ): MessageDto

}