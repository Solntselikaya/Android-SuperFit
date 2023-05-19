package com.example.superfit.data.repository.storage

import com.example.superfit.data.dto.AccessTokenDto
import com.example.superfit.data.storage.encrypted.EncryptedSharedPreferencesStorage
import com.example.superfit.domain.repository.storage.TokenRepository

class TokenRepositoryImpl(
    private val encryptedSharedPreferencesStorage: EncryptedSharedPreferencesStorage
): TokenRepository {

    /*private val encryptedSharedPreferencesStorage by lazy {
        EncryptedSharedPreferencesStorage()
    }*/

    override fun saveTokenToLocalStorage(token: AccessTokenDto) {
        encryptedSharedPreferencesStorage.saveToken(token)
    }

    override fun getTokenFromLocalStorage(): AccessTokenDto {
        return encryptedSharedPreferencesStorage.getToken()
    }

    override fun deleteTokenFromLocalStorage() {
        encryptedSharedPreferencesStorage.deleteToken()
    }
}