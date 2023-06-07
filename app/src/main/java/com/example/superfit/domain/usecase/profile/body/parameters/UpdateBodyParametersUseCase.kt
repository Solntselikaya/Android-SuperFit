package com.example.superfit.domain.usecase.profile.body.parameters

import com.example.superfit.data.dto.BodyParametersDto
import com.example.superfit.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UpdateBodyParametersUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(
        height: Int?,
        weight: Int?
    ) {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.now().format(formatter)

        withContext(Dispatchers.IO) {
            repository.updateBodyParameters(
                BodyParametersDto(date, height ?: 10, weight ?: 10)
            )
        }
    }
}