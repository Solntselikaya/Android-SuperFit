package com.example.superfit.domain.usecase.profile.body.parameters

import com.example.superfit.data.dto.BodyParametersDto
import com.example.superfit.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UpdateBodyParametersUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(
        date: String,
        height: Int,
        weight: Int
    ) {
        withContext(Dispatchers.IO) {
            repository.updateBodyParameters(
                BodyParametersDto(date, height, weight)
            )
        }
    }
}