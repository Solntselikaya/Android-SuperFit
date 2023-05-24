package com.example.superfit.domain.usecase.training.db

import com.example.superfit.common.TrainingType
import com.example.superfit.data.db.dao.ExercisesDao
import com.example.superfit.data.db.entities.ExerciseEntity
import com.example.superfit.domain.usecase.db.GetExerciseRepeatCountFromDatabaseUseCase
import com.example.superfit.domain.usecase.storage.credentials.GetUserEmailFromLocalStorageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetExerciseRepeatCountForUserFromDatabaseUseCase(
    private val getUserEmailFromLocalStorageUseCase: GetUserEmailFromLocalStorageUseCase,
    private val getExerciseRepeatCountFromDatabaseUseCase: GetExerciseRepeatCountFromDatabaseUseCase
) {
    suspend operator fun invoke(exerciseType: String): Int =
        withContext(Dispatchers.IO) {
            val email = getUserEmailFromLocalStorageUseCase.execute()
            getExerciseRepeatCountFromDatabaseUseCase(email, exerciseType)
        }
}