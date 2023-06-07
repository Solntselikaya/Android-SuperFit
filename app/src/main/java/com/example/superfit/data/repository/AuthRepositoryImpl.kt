package com.example.superfit.data.repository

import com.example.superfit.data.api.AuthApi
import com.example.superfit.data.dto.AccessTokenDto
import com.example.superfit.data.dto.AuthCredentialsDto
import com.example.superfit.data.dto.AuthResponseDto
import com.example.superfit.data.dto.RefreshTokenDto
import com.example.superfit.data.dto.RegistrationDto
import com.example.superfit.domain.repository.AuthRepository
import retrofit2.Response

class AuthRepositoryImpl(
    private val api: AuthApi
) : AuthRepository {

    override suspend fun login(body: AuthCredentialsDto): AuthResponseDto {
        return api.login(body)
    }

    override suspend fun getToken(body: RefreshTokenDto): AccessTokenDto {
        return api.getToken(body)
    }

    override suspend fun register(body: RegistrationDto): Response<Void> {
        return api.register(body)
    }

}