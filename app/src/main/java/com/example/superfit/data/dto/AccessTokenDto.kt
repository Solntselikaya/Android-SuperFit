package com.example.superfit.data.dto

import com.example.superfit.domain.model.AccessTokenModel

data class AccessTokenDto(
    val access_token: String,
    val expired: Int
)

fun AccessTokenDto.toAccessTokenModel(): AccessTokenModel =
    AccessTokenModel(
        access_token = access_token,
        expired = expired
    )