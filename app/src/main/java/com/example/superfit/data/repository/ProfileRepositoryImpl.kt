package com.example.superfit.data.repository

import com.example.superfit.data.remote.ProfileApi
import com.example.superfit.data.remote.dto.BodyParametersDto
import com.example.superfit.data.remote.dto.MessageDto
import com.example.superfit.data.remote.dto.ProfileInfoDto
import com.example.superfit.data.remote.dto.ProfilePhotoDto
import com.example.superfit.domain.repository.ProfileRepository
import okhttp3.MultipartBody

class ProfileRepositoryImpl(
    private val api: ProfileApi
): ProfileRepository {

    override suspend fun getProfileInfo(): ProfileInfoDto {
        return api.getProfileInfo()
    }

    override suspend fun updateBodyParameters(body: BodyParametersDto): MessageDto {
        return api.updateBodyParameters(body)
    }

    override suspend fun getBodyParametersHistory(): List<BodyParametersDto> {
        return api.getBodyParametersHistory()
    }

    override suspend fun getPhotos(): List<ProfilePhotoDto> {
        return api.getPhotos()
    }

    override suspend fun uploadPhoto(image: MultipartBody.Part): ProfilePhotoDto {
        return api.uploadPhoto(image)
    }

    override suspend fun getPhotoById(id: String): String {
        return api.getPhotoById(id)
    }

    override suspend fun deletePhotoById(id: String): MessageDto {
        return api.deletePhotoById(id)
    }

}