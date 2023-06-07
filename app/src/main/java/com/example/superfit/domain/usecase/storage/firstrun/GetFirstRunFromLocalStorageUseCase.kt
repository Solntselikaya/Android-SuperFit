package com.example.superfit.domain.usecase.storage.firstrun

import com.example.superfit.domain.repository.storage.FirstRunStorageRepository

class GetFirstRunFromLocalStorageUseCase(
    private val firstRunStorageRepository: FirstRunStorageRepository
) {
    fun execute(): Boolean {
        return firstRunStorageRepository.getFirstRunFromUserStorage()
    }
}