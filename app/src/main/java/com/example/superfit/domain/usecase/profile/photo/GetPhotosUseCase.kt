package com.example.superfit.domain.usecase.profile.photo

import com.example.superfit.data.dto.toProfilePhotoModel
import com.example.superfit.domain.model.PhotoModel
import com.example.superfit.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPhotosUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): List<PhotoModel> {
        val photos = mutableListOf<PhotoModel>()

        withContext(Dispatchers.IO) {
            val photoIdList = repository.getPhotos().map { it.toProfilePhotoModel() }

            for (photo in photoIdList) {
                photos.add(
                    PhotoModel(repository.getPhotoById(photo.id))
                )
            }
        }

        return photos
    }
}