package com.example.superfit.domain.model

import com.example.superfit.data.dto.AccessTokenDto

data class AccessTokenModel(
    val access_token: String,
    val expired: Long
)

fun AccessTokenModel.toAccessTokenDto(): AccessTokenDto =
    AccessTokenDto(
        access_token = access_token,
        expired = expired
    )