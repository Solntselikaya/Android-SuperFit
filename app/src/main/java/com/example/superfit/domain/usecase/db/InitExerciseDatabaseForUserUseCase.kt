package com.example.superfit.domain.usecase.db

import com.example.superfit.common.TrainingType
import com.example.superfit.domain.usecase.storage.credentials.GetUserEmailFromLocalStorageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InitExerciseDatabaseForUserUseCase(
    private val insertExerciseToDatabaseUseCase: InsertExerciseToDatabaseUseCase,
    private val getUserEmailFromLocalStorageUseCase: GetUserEmailFromLocalStorageUseCase
) {

    suspend operator fun invoke() =
        withContext(Dispatchers.IO) {
            val email = getUserEmailFromLocalStorageUseCase.execute()

            enumValues<TrainingType>().forEach { training ->
                insertExerciseToDatabaseUseCase(
                    email,
                    training,
                    training.defaultMinCount
                )
            }
        }
}