package com.example.superfit.domain.usecase.training.api

import com.example.superfit.data.dto.TrainingDto
import com.example.superfit.data.dto.toTrainingModel
import com.example.superfit.domain.model.TrainingModel
import com.example.superfit.domain.repository.TrainingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SaveUserTrainingUseCase(
    private val repository: TrainingRepository
) {
    suspend operator fun invoke(
        exercise: String,
        repeatCount: Int
    ): TrainingModel =
        withContext(Dispatchers.IO) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val current = LocalDate.now().format(formatter)

            repository.save(
                TrainingDto(current, exercise, repeatCount)
            ).toTrainingModel()
        }
}