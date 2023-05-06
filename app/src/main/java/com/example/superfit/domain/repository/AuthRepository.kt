package com.example.superfit.domain.repository

import com.example.superfit.data.dto.*

interface AuthRepository {

    suspend fun login(body: AuthCredntialsDto): AuthResponceDto

    suspend fun getToken(body: RefreshTokenDto): AcessTokenDto

    suspend fun register(body: RegistartionDto): MessageDto

}