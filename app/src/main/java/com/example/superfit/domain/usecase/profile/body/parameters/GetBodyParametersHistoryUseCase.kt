package com.example.superfit.domain.usecase.profile.body.parameters

import com.example.superfit.data.dto.toBodyParametersModel
import com.example.superfit.domain.model.BodyParametersModel
import com.example.superfit.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetBodyParametersHistoryUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): List<BodyParametersModel> =
        withContext(Dispatchers.IO) {
            repository.getBodyParametersHistory().map { it.toBodyParametersModel() }
        }
}