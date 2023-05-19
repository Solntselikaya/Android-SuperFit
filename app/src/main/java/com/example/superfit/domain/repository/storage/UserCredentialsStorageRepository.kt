package com.example.superfit.domain.repository.storage

interface UserCredentialsStorageRepository {
    fun saveUserEmailToStorage(email: String)

    fun getUserEmailFromStorage(): String

    fun deleteUserEmailFromStorage()
}