package com.example.superfit.data.api

import com.example.superfit.data.dto.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {

    @POST("auth/token")
    suspend fun login(
        @Body body: AuthCredentialsDto
    ): AuthResponseDto

    @POST("auth/token/refresh")
    suspend fun getToken(
        @Body body: RefreshTokenDto
    ): AccessTokenDto

    @POST("auth/register")
    suspend fun register(
        @Body body: RegistrationDto
    ): Response<Void>

}