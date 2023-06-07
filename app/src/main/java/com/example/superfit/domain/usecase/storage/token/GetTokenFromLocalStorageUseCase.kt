package com.example.superfit.domain.usecase.storage.token

import com.example.superfit.data.dto.AccessTokenDto
import com.example.superfit.domain.repository.storage.TokenRepository

class GetTokenFromLocalStorageUseCase(
    private val tokenRepository: TokenRepository
) {
    fun execute(): AccessTokenDto {
        return tokenRepository.getTokenFromLocalStorage()
    }
}