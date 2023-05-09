package com.example.superfit.domain.repository

import com.example.superfit.data.dto.*
import retrofit2.Response

interface AuthRepository {

    suspend fun login(body: AuthCredentialsDto): AuthResponseDto

    suspend fun getToken(body: RefreshTokenDto): AccessTokenDto

    suspend fun register(body: RegistrationDto): Response<Void>

}