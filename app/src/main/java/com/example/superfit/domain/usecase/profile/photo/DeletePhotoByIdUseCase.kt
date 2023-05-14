package com.example.superfit.domain.usecase.profile.photo

import com.example.superfit.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class DeletePhotoByIdUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(id: String): Response<Void> =
        withContext(Dispatchers.IO) {
            repository.deletePhotoById(id)
        }
    }