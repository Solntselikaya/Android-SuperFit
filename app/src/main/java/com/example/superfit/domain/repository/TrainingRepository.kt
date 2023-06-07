package com.example.superfit.domain.repository

import com.example.superfit.data.dto.TrainingDto

interface TrainingRepository {

    suspend fun get(): List<TrainingDto>

    suspend fun save(body: TrainingDto): TrainingDto

}