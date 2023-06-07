package com.example.superfit.domain.usecase.storage.firstrun

import com.example.superfit.domain.repository.storage.FirstRunStorageRepository

class SaveFirstRunInLocalStorageUseCase(
    private val firstRunStorageRepository: FirstRunStorageRepository
) {
    fun execute() {
        firstRunStorageRepository.saveFirstRunToUserStorage()
    }
}