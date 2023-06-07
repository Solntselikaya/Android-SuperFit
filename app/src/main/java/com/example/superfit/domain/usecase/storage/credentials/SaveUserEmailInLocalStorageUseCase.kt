package com.example.superfit.domain.usecase.storage.credentials

import com.example.superfit.domain.repository.storage.UserCredentialsStorageRepository

class SaveUserEmailInLocalStorageUseCase(
    private val userCredentialsStorageRepository: UserCredentialsStorageRepository
) {
    fun execute(email: String) {
        userCredentialsStorageRepository.saveUserEmailToStorage(email)
    }
}