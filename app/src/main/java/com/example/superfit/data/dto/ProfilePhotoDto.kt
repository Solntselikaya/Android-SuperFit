package com.example.superfit.data.dto

import com.example.superfit.domain.model.ProfilePhotoModel

data class ProfilePhotoDto(
    val id: String,
    val uploaded: Long
)

fun ProfilePhotoDto.toProfilePhotoModel(): ProfilePhotoModel =
    ProfilePhotoModel(
        date = uploaded,
        id = id
    )