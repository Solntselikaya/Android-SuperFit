package com.example.superfit.domain.usecase.auth

import com.example.superfit.data.dto.AuthCredentialsDto
import com.example.superfit.data.dto.RefreshTokenDto
import com.example.superfit.data.dto.toAccessTokenModel
import com.example.superfit.data.dto.toAuthResponseModel
import com.example.superfit.domain.model.AccessTokenModel
import com.example.superfit.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(login: String, password: String): AccessTokenModel {

        var token: AccessTokenModel
        withContext(Dispatchers.IO) {
            val refreshToken = repository.login(
                AuthCredentialsDto(login, password)
            ).toAuthResponseModel().refresh_token

            token = repository.getToken(
                RefreshTokenDto(refreshToken)
            ).toAccessTokenModel()
        }

        return token
    }
}