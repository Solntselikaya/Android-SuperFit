package com.example.superfit.domain.usecase.db

import com.example.superfit.data.db.dao.ExercisesDao
import com.example.superfit.domain.usecase.storage.credentials.GetUserEmailFromLocalStorageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DeleteAllForUserFromDatabaseUseCase(
    private val dao: ExercisesDao,
    private val getUserEmailFromLocalStorageUseCase: GetUserEmailFromLocalStorageUseCase
) {
    suspend operator fun invoke() =
        withContext(Dispatchers.IO) {
            val email = getUserEmailFromLocalStorageUseCase.execute()
            dao.deleteAllForUser(email)
        }
}