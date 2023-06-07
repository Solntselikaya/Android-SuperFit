package com.example.superfit.domain.usecase.profile.body.parameters

import com.example.superfit.data.dto.toBodyParametersModel
import com.example.superfit.domain.model.BodyParametersModel
import com.example.superfit.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

class GetBodyParametersHistoryUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): List<BodyParametersModel> =
        withContext(Dispatchers.IO) {
            repository.getBodyParametersHistory().sortedByDescending { getDateFromString(it.date) }.map { it.toBodyParametersModel() }
        }

    private fun getDateFromString(str: String): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return LocalDate.parse(str, formatter)
    }
}