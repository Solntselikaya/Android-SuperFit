package com.example.superfit.domain.usecase.profile.photo

import com.example.superfit.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPhotoByIdUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(id: String): String =
        withContext(Dispatchers.IO) {
            repository.getPhotoById(id)
        }
}