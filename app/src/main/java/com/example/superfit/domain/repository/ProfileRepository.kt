package com.example.superfit.domain.repository

import com.example.superfit.data.remote.dto.BodyParametersDto
import com.example.superfit.data.remote.dto.MessageDto
import com.example.superfit.data.remote.dto.ProfileInfoDto
import com.example.superfit.data.remote.dto.ProfilePhotoDto
import okhttp3.MultipartBody

interface ProfileRepository {

    suspend fun getProfileInfo(): ProfileInfoDto

    suspend fun updateBodyParameters(body: BodyParametersDto): MessageDto

    suspend fun getBodyParametersHistory(): List<BodyParametersDto>

    suspend fun getPhotos(): List<ProfilePhotoDto>

    suspend fun uploadPhoto(image: MultipartBody.Part): ProfilePhotoDto

    suspend fun getPhotoById(id: String): String

    suspend fun deletePhotoById(id: String): MessageDto

}