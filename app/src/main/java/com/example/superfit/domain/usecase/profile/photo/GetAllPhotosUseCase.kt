package com.example.superfit.domain.usecase.profile.photo

import com.example.superfit.common.getImageBitmap
import com.example.superfit.data.dto.toProfilePhotoModel
import com.example.superfit.domain.model.PhotoModel
import com.example.superfit.domain.repository.ProfileRepository
import com.example.superfit.presentation.common.PhotoData
import com.example.superfit.presentation.common.extensions.toDateString
import com.example.superfit.presentation.common.extensions.toDateStringWithDots
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllPhotosUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): List<PhotoData> {
        val photos = mutableListOf<PhotoData>()

        withContext(Dispatchers.IO) {
            val photoIdList = repository.getPhotos().sortedByDescending { it.uploaded }.map { it.toProfilePhotoModel() }

            for (photo in photoIdList) {
                photos.add(
                    PhotoData(
                        photo.date.toDateStringWithDots(),
                        getImageBitmap(repository.getPhotoById(photo.id))
                    )
                )
            }
        }

        return photos
    }
}