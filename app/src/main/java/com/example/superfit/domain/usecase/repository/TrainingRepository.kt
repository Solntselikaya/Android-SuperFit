package com.example.superfit.domain.usecase.repository

import com.example.superfit.data.remote.dto.TrainingDto

interface TrainingRepository {

    suspend fun get(): List<TrainingDto>

    suspend fun save(body: TrainingDto): TrainingDto

}