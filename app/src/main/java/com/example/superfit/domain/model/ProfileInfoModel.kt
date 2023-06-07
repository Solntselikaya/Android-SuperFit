package com.example.superfit.domain.model

import com.example.superfit.data.dto.ProfileInfoDto

data class ProfileInfoModel(
    val login: String
)

fun ProfileInfoModel.toProfileInfoDto(): ProfileInfoDto =
    ProfileInfoDto(login = login)