package com.example.superfit.data.remote

import com.example.superfit.data.remote.dto.TrainingDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TrainingApi {

    @GET("trainings")
    suspend fun get(): List<TrainingDto>

    @POST("trainings")
    suspend fun save(
        @Body body: TrainingDto
    ): TrainingDto

}