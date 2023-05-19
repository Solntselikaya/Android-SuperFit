package com.example.superfit.data.repository.storage

import com.example.superfit.data.storage.encrypted.EncryptedSharedPreferencesStorage
import com.example.superfit.domain.repository.storage.UserCredentialsStorageRepository

class UserCredentialsStorageRepositoryImpl(
    private val encryptedSharedPreferencesStorage: EncryptedSharedPreferencesStorage
): UserCredentialsStorageRepository {
    override fun saveUserEmailToStorage(email: String) {
        encryptedSharedPreferencesStorage.saveUserEmail(email)
    }

    override fun getUserEmailFromStorage(): String {
        return encryptedSharedPreferencesStorage.getUserEmail()
    }

    override fun deleteUserEmailFromStorage() {
        encryptedSharedPreferencesStorage.deleteUserEmail()
    }
}