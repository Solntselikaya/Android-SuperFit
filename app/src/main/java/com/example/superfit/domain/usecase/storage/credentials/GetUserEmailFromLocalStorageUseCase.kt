package com.example.superfit.domain.usecase.storage.credentials

import com.example.superfit.domain.repository.storage.UserCredentialsStorageRepository

class GetUserEmailFromLocalStorageUseCase(
    private val userCredentialsStorageRepository: UserCredentialsStorageRepository
) {
    fun execute(): String {
        return userCredentialsStorageRepository.getUserEmailFromStorage()
    }
}