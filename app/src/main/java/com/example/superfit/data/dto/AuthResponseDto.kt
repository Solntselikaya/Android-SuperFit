package com.example.superfit.data.dto

import com.example.superfit.domain.model.AuthResponseModel

data class AuthResponseDto(
    val expired: Int,
    val refresh_token: String,
    val username: String
)

fun AuthResponseDto.toAuthResponseModel(): AuthResponseModel =
    AuthResponseModel(
        expired = expired,
        refresh_token = refresh_token,
        username = username
    )