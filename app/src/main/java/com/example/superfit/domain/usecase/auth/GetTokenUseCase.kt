package com.example.superfit.domain.usecase.auth

import com.example.superfit.data.dto.RefreshTokenDto
import com.example.superfit.data.dto.toAccessTokenModel
import com.example.superfit.domain.model.AccessTokenModel
import com.example.superfit.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetTokenUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(refreshToken: String): AccessTokenModel =
        withContext(Dispatchers.IO) {
            repository.getToken(
                RefreshTokenDto(refreshToken)
            ).toAccessTokenModel()
        }
}