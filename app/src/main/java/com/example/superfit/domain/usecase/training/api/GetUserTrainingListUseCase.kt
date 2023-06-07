package com.example.superfit.domain.usecase.training.api

import com.example.superfit.data.dto.toTrainingModel
import com.example.superfit.domain.model.TrainingModel
import com.example.superfit.domain.repository.TrainingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUserTrainingListUseCase(
    private val repository: TrainingRepository
) {
    suspend operator fun invoke(): List<TrainingModel> =
        withContext(Dispatchers.IO) {
            repository.get().sortedByDescending { it.date }.map { it.toTrainingModel() }
        }
}