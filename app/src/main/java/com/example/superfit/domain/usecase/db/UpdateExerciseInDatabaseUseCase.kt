package com.example.superfit.domain.usecase.db

import com.example.superfit.common.TrainingType
import com.example.superfit.data.db.dao.ExercisesDao
import com.example.superfit.data.db.entities.ExerciseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateExerciseInDatabaseUseCase(
    private val dao: ExercisesDao
) {
    suspend operator fun invoke(
        userEmail: String,
        exerciseType: String,
        repeatCount: Int
    ) = withContext(Dispatchers.IO) {
        dao.update(
            exercise = ExerciseEntity(
                userEmail = userEmail,
                exerciseType = exerciseType,
                count = repeatCount
            )
        )
    }
}