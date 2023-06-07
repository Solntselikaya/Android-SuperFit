package com.example.superfit.domain.usecase.storage.credentials

import com.example.superfit.domain.repository.storage.UserCredentialsStorageRepository

class DeleteUserEmailFromLocalStorageUseCase(
    private val userCredentialsStorageRepository: UserCredentialsStorageRepository
) {
    fun execute() {
        userCredentialsStorageRepository.deleteUserEmailFromStorage()
    }
}