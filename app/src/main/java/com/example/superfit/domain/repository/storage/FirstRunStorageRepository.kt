package com.example.superfit.domain.repository.storage

interface FirstRunStorageRepository {
    fun saveFirstRunToUserStorage()

    fun getFirstRunFromUserStorage(): Boolean

    fun deleteFirstRunFromUserStorage()
}