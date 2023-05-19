package com.example.superfit.data.storage.encrypted

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.superfit.data.dto.AccessTokenDto
import com.example.superfit.data.storage.encrypted.UserCredentialsStorage.Companion.USER_EMAIL_KEY

class EncryptedSharedPreferencesStorage(context: Context) : TokenStorage, UserCredentialsStorage {

    companion object {
        const val ENCRYPTED_SHARED_PREFS_NAME = "encryptedSharedPreferences"
    }

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        ENCRYPTED_SHARED_PREFS_NAME,
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )


    override fun saveToken(token: AccessTokenDto) {
        sharedPreferences.edit()
            .putString(TokenStorage.TOKEN_KEY, token.access_token)
            .putInt(TokenStorage.TOKEN_EXPIRE_TIME, token.expired)
            .apply()
    }

    override fun getToken(): AccessTokenDto {
        return AccessTokenDto(
            sharedPreferences.getString(
                TokenStorage.TOKEN_KEY, ""
            ).toString(),
            sharedPreferences.getInt(
                TokenStorage.TOKEN_EXPIRE_TIME, 0
            )
        )
    }

    override fun deleteToken() {
        sharedPreferences.edit()
            .remove(TokenStorage.TOKEN_KEY)
            .apply()
    }

    override fun saveUserEmail(email: String) {
        sharedPreferences.edit().putString(USER_EMAIL_KEY, email).apply()
    }

    override fun getUserEmail(): String {
        return sharedPreferences.getString(USER_EMAIL_KEY, "").toString()
    }

    override fun deleteUserEmail() {
        sharedPreferences.edit().remove(USER_EMAIL_KEY).apply()
    }
}