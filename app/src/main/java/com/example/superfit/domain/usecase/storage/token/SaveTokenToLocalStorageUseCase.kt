package com.example.superfit.domain.usecase.storage.token

import com.example.superfit.data.dto.AccessTokenDto
import com.example.superfit.domain.repository.storage.TokenRepository

class SaveTokenToLocalStorageUseCase(
    private val tokenRepository: TokenRepository
) {
    fun execute(token: AccessTokenDto) {
        tokenRepository.saveTokenToLocalStorage(token)
    }
}