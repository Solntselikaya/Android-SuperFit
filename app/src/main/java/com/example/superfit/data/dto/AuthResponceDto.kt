package com.example.superfit.data.dto

data class AuthResponceDto(
    val expired: Int,
    val refresh_token: String,
    val username: String
)