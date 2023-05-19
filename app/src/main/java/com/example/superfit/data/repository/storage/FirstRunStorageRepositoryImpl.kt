package com.example.superfit.data.repository.storage

import com.example.superfit.data.storage.firstrun.FirstRunSharedPreferencesStorage
import com.example.superfit.domain.repository.storage.FirstRunStorageRepository

class FirstRunStorageRepositoryImpl(
    private val firstRunSharedPreferencesStorage: FirstRunSharedPreferencesStorage
): FirstRunStorageRepository {
    override fun saveFirstRunToUserStorage() {
        firstRunSharedPreferencesStorage.saveFirstRun()
    }

    override fun getFirstRunFromUserStorage(): Boolean {
        return firstRunSharedPreferencesStorage.getFirstRun()
    }

    override fun deleteFirstRunFromUserStorage() {
        firstRunSharedPreferencesStorage.deleteFirstRun()
    }
}