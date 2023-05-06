package com.example.superfit.data.remote

import com.example.superfit.data.remote.dto.BodyParametersDto
import com.example.superfit.data.remote.dto.MessageDto
import com.example.superfit.data.remote.dto.ProfileInfoDto
import com.example.superfit.data.remote.dto.ProfilePhotoDto
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ProfileApi {

    @GET("profile")
    suspend fun getProfileInfo(): ProfileInfoDto

    @POST("profile/params")
    suspend fun updateBodyParameters(
        @Body body: BodyParametersDto
    ): MessageDto

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
    ): MessageDto

}