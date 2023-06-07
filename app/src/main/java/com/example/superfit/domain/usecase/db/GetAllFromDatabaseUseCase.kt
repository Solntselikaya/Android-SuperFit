package com.example.superfit.domain.usecase.db

import com.example.superfit.data.db.dao.ExercisesDao
import com.example.superfit.data.db.entities.toTrainingModel
import com.example.superfit.domain.model.TrainingModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetAllFromDatabaseUseCase(
    private val dao: ExercisesDao
) {
    suspend operator fun invoke(userEmail: String): List<TrainingModel> =
        withContext(Dispatchers.IO) {
            dao.getAllForUser(userEmail).map { it.toTrainingModel() }
        }
}