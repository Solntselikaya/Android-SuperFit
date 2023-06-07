package com.example.superfit.domain.usecase.profile.photo

import com.example.superfit.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class UploadPhotoUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(photo: ByteArray) {
        val body = photo.toRequestBody("image/*".toMediaType(), 0, photo.size)
        val part = MultipartBody.Part.createFormData("file", "image", body)

        withContext(Dispatchers.IO) {
            repository.uploadPhoto(part)
        }
    }
}