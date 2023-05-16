package com.example.superfit.domain.usecase.token

import android.content.Context
import com.example.superfit.domain.repository.TokenRepository

class DeleteTokenFromLocalStorageUseCase(
    private val context: Context
) {
    private val tokenRepositoryImpl by lazy {
        TokenRepository(context)
    }

    fun execute() {
        tokenRepositoryImpl.deleteTokenFromLocalStorage()
    }
}