package com.example.superfit.domain.usecase.training

import com.example.superfit.data.dto.TrainingDto
import com.example.superfit.data.dto.toTrainingModel
import com.example.superfit.domain.model.TrainingModel
import com.example.superfit.domain.repository.TrainingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveUserTrainingUseCase(
    private val repository: TrainingRepository
) {
    suspend operator fun invoke(
        date: String,
        exercise: String,
        repeatCount: Int
    ): TrainingModel =
        withContext(Dispatchers.IO) {
            repository.save(
                TrainingDto(date, exercise, repeatCount)
            ).toTrainingModel()
        }
}