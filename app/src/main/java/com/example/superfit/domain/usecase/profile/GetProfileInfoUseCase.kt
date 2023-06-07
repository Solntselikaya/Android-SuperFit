package com.example.superfit.domain.usecase.profile

import com.example.superfit.data.dto.toProfileInfoModel
import com.example.superfit.domain.model.ProfileInfoModel
import com.example.superfit.domain.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetProfileInfoUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): ProfileInfoModel =
        withContext(Dispatchers.IO) {
            repository.getProfileInfo().toProfileInfoModel()
        }
}