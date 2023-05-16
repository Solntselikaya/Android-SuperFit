package com.example.superfit.domain.usecase.token

import android.content.Context
import com.example.superfit.data.dto.AccessTokenDto
import com.example.superfit.domain.repository.TokenRepository

class SaveTokenToLocalStorageUseCase(
    private val context: Context
) {
    private val tokenRepository by lazy {
        TokenRepository(context)
    }

    fun execute(token: AccessTokenDto) {
        tokenRepository.saveTokenToLocalStorage(token)
    }
}