package com.example.superfit.data.storage

import com.example.superfit.data.dto.AccessTokenDto

interface TokenStorage {
    companion object {
        const val TOKEN_KEY = "userToken"
        const val TOKEN_EXPIRE_TIME = "tokenExpireTime"
    }

    fun saveToken(token: AccessTokenDto)

    fun getToken(): AccessTokenDto

    fun deleteToken()
}