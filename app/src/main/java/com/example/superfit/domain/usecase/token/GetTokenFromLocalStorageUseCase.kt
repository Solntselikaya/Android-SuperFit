package com.example.superfit.domain.usecase.token

import android.content.Context
import com.example.superfit.data.dto.AccessTokenDto
import com.example.superfit.domain.repository.TokenRepository

class GetTokenFromLocalStorageUseCase(
    private val context: Context
) {
    private val tokenRepositoryImpl by lazy {
        TokenRepository(context)
    }

    fun execute(): AccessTokenDto {
        return tokenRepositoryImpl.getTokenFromLocalStorage()
    }
}