package com.example.superfit.domain.model

import com.example.superfit.data.dto.BodyParametersDto

data class BodyParametersModel(
    val date: String,
    val height: Int,
    val weight: Int
)

fun BodyParametersModel.toBodyParametersDto(): BodyParametersDto =
    BodyParametersDto(date, height, weight)