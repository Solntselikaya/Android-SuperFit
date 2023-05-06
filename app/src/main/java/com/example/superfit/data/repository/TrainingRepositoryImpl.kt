package com.example.superfit.data.repository

import com.example.superfit.data.remote.TrainingApi
import com.example.superfit.data.remote.dto.TrainingDto
import com.example.superfit.domain.usecase.repository.TrainingRepository

class TrainingRepositoryImpl(
    private val api: TrainingApi
): TrainingRepository {

    override suspend fun get(): List<TrainingDto> {
        return api.get()
    }

    override suspend fun save(body: TrainingDto): TrainingDto {
        return api.save(body)
    }

}