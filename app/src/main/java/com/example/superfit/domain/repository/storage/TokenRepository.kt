package com.example.superfit.domain.repository.storage

import com.example.superfit.data.dto.AccessTokenDto

interface TokenRepository {
    fun saveTokenToLocalStorage(token: AccessTokenDto)

    fun getTokenFromLocalStorage(): AccessTokenDto

    fun deleteTokenFromLocalStorage()
}