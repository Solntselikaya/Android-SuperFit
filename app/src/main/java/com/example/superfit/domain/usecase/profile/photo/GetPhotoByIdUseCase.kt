package com.example.superfit.domain.usecase.profile.photo

import com.example.superfit.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class GetPhotoByIdUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(id: String): ResponseBody =
        withContext(Dispatchers.IO) {
            repository.getPhotoById(id)
        }
}