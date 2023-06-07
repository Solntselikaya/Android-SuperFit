package com.example.superfit.domain.usecase.profile.photo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.superfit.common.getImageBitmap
import com.example.superfit.data.dto.toProfilePhotoModel
import com.example.superfit.domain.model.PhotoModel
import com.example.superfit.domain.repository.ProfileRepository
import com.example.superfit.presentation.common.extensions.toDateString
import com.example.superfit.presentation.common.extensions.toDateStringWithDots
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class GetProgressPhotosUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): List<Pair<Bitmap?, String?>> =
        withContext(Dispatchers.IO) {
            val photoList = repository.getPhotos().sortedByDescending { it.uploaded }.map { it.toProfilePhotoModel() }

            when (photoList.size) {
                0 -> {
                    listOf(
                        Pair(null, null),
                        Pair(null, null)
                    )
                }

                1 -> {
                    val firstPhoto = repository.getPhotoById(photoList.last().id)

                    listOf(
                        Pair(
                            getImageBitmap(firstPhoto),
                            photoList.last().date.toDateStringWithDots()
                        ),
                        Pair(null, null)
                    )
                }

                else -> {
                    val firstPhoto = repository.getPhotoById(photoList.last().id)
                    val lastPhoto = repository.getPhotoById(photoList.first().id)

                    listOf(
                        Pair(
                            getImageBitmap(firstPhoto),
                            photoList.last().date.toDateStringWithDots()
                        ),
                        Pair(
                            getImageBitmap(lastPhoto),
                            photoList.first().date.toDateStringWithDots()
                        )
                    )
                }
            }
        }
}