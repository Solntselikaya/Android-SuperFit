package com.example.superfit.data.dto

import com.example.superfit.domain.model.ProfilePhotoModel

data class ProfilePhotoDto(
    val id: String,
    val uploaded: Int
)

fun ProfilePhotoDto.toProfilePhotoModel(): ProfilePhotoModel =
    ProfilePhotoModel(id = id)