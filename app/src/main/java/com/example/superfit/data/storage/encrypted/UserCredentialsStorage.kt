package com.example.superfit.data.storage.encrypted

interface UserCredentialsStorage {
    companion object {
        const val USER_EMAIL_KEY = "userEmail"
    }

    fun saveUserEmail(email: String)

    fun getUserEmail(): String

    fun deleteUserEmail()
}