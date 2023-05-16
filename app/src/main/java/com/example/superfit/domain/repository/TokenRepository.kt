package com.example.superfit.domain.repository

import android.content.Context
import com.example.superfit.data.dto.AccessTokenDto
import com.example.superfit.data.storage.SharedPreferencesStorage

class TokenRepository(
    context: Context
) {

    private val sharedPreferencesStorage by lazy {
        SharedPreferencesStorage(context)
    }

    fun saveTokenToLocalStorage(token: AccessTokenDto) {
        sharedPreferencesStorage.saveToken(token)
    }

    fun getTokenFromLocalStorage(): AccessTokenDto {
        return sharedPreferencesStorage.getToken()
    }

    fun deleteTokenFromLocalStorage() {
        sharedPreferencesStorage.deleteToken()
    }
}