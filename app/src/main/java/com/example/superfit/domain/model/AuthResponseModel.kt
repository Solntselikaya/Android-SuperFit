package com.example.superfit.domain.model

import com.example.superfit.data.dto.AuthResponseDto

data class AuthResponseModel(
    val expired: Int,
    val refresh_token: String,
    val username: String
)

fun AuthResponseModel.toAuthResponseDto(): AuthResponseDto =
    AuthResponseDto(
        expired = expired,
        refresh_token = refresh_token,
        username = username
    )