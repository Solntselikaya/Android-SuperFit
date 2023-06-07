package com.example.superfit.data.repository

import com.example.superfit.data.api.ProfileApi
import com.example.superfit.data.dto.BodyParametersDto
import com.example.superfit.data.dto.ProfileInfoDto
import com.example.superfit.data.dto.ProfilePhotoDto
import com.example.superfit.domain.repository.ProfileRepository
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response

class ProfileRepositoryImpl(
    private val api: ProfileApi
) : ProfileRepository {

    override suspend fun getProfileInfo(): ProfileInfoDto {
        return api.getProfileInfo()
    }

    override suspend fun updateBodyParameters(body: BodyParametersDto): Response<Void> {
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

    override suspend fun getPhotoById(id: String): ResponseBody {
        return api.getPhotoById(id)
    }

    override suspend fun deletePhotoById(id: String): Response<Void> {
        return api.deletePhotoById(id)
    }

}