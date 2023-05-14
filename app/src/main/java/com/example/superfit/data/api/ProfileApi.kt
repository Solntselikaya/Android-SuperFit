package com.example.superfit.data.api

import com.example.superfit.data.dto.BodyParametersDto
import com.example.superfit.data.dto.ProfileInfoDto
import com.example.superfit.data.dto.ProfilePhotoDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ProfileApi {

    @GET("profile")
    suspend fun getProfileInfo(): ProfileInfoDto

    @POST("profile/params")
    suspend fun updateBodyParameters(
        @Body body: BodyParametersDto
    ): Response<Void>

    @GET("profile/params/history")
    suspend fun getBodyParametersHistory(): List<BodyParametersDto>

    @GET("profile/photos")
    suspend fun getPhotos(): List<ProfilePhotoDto>

    @POST("profile/photos")
    suspend fun uploadPhoto(
        @Part image: MultipartBody.Part
    ): ProfilePhotoDto

    @GET("profile/photos/{id}")
    suspend fun getPhotoById(
        @Path("id") photoId: String
    ): String

    @DELETE("profile/photos/{id}")
    suspend fun deletePhotoById(
        @Path("id") photoId: String
    ): Response<Void>

}