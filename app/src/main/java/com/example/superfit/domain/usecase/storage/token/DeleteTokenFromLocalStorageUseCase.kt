package com.example.superfit.domain.usecase.storage.token

import com.example.superfit.domain.repository.storage.TokenRepository

class DeleteTokenFromLocalStorageUseCase(
    private val tokenRepository: TokenRepository
) {
    fun execute() {
        tokenRepository.deleteTokenFromLocalStorage()
    }
}