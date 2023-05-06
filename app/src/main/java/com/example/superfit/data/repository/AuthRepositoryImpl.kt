package com.example.superfit.data.repository

import com.example.superfit.data.remote.AuthApi
import com.example.superfit.data.remote.dto.*
import com.example.superfit.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi
): AuthRepository {

    override suspend fun login(body: AuthCredntialsDto): AuthResponceDto {
        return api.login(body)
    }

    override suspend fun getToken(body: RefreshTokenDto): AcessTokenDto {
        return api.getToken(body)
    }

    override suspend fun register(body: RegistartionDto): MessageDto {
        return api.register(body)
    }

}