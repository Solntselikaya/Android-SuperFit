package com.example.superfit.domain.usecase.db

import com.example.superfit.data.db.dao.ExercisesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteAllFromDatabaseUseCase(
    private val dao: ExercisesDao
) {
    suspend operator fun invoke() =
        withContext(Dispatchers.IO) {
            dao.deleteAll()
        }
}