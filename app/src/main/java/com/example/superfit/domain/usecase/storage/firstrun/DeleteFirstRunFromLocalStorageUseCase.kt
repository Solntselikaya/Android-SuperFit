package com.example.superfit.domain.usecase.storage.firstrun

import com.example.superfit.domain.repository.storage.FirstRunStorageRepository

class DeleteFirstRunFromLocalStorageUseCase(
    private val firstRunStorageRepository: FirstRunStorageRepository
) {
    fun execute() {
        firstRunStorageRepository.deleteFirstRunFromUserStorage()
    }
}