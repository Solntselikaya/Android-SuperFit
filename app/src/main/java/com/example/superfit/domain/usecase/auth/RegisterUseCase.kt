package com.example.superfit.domain.usecase.auth

import com.example.superfit.data.dto.RegistrationDto
import com.example.superfit.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class RegisterUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(login: String, password: String): Response<Void> =
        withContext(Dispatchers.IO) {
            repository.register(RegistrationDto(login, password))
        }
}