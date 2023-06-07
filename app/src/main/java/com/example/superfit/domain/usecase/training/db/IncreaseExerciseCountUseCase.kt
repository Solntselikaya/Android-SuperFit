package com.example.superfit.domain.usecase.training.db

import com.example.superfit.common.TrainingType
import com.example.superfit.domain.usecase.db.GetExerciseRepeatCountFromDatabaseUseCase
import com.example.superfit.domain.usecase.db.UpdateExerciseInDatabaseUseCase
import com.example.superfit.domain.usecase.storage.credentials.GetUserEmailFromLocalStorageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IncreaseExerciseCountUseCase(
    private val getUserEmailFromLocalStorageUseCase: GetUserEmailFromLocalStorageUseCase,
    private val getExerciseRepeatCountFromDatabaseUseCase: GetExerciseRepeatCountFromDatabaseUseCase,
    private val updateExerciseInDatabaseUseCase: UpdateExerciseInDatabaseUseCase
) {
    suspend operator fun invoke(exercise: String) =
        withContext(Dispatchers.IO) {
            val userEmail = getUserEmailFromLocalStorageUseCase.execute()
            val currCount = getExerciseRepeatCountFromDatabaseUseCase(
                userEmail,
                exercise
            )

            val newCount = when (TrainingType.valueOf(exercise)) {
                TrainingType.RUNNING -> currCount + 100
                else -> currCount + 5
            }
            updateExerciseInDatabaseUseCase(userEmail, exercise, newCount)
        }
}