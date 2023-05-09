package com.example.superfit.data.dto

import com.example.superfit.domain.model.ProfileInfoModel

data class ProfileInfoDto(
    val login: String
)

fun ProfileInfoDto.toProfileInfoModel(): ProfileInfoModel =
    ProfileInfoModel(login = login)