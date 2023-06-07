package com.example.superfit.data.dto

import com.example.superfit.domain.model.BodyParametersModel

data class BodyParametersDto(
    val date: String,
    val height: Int,
    val weight: Int
)

fun BodyParametersDto.toBodyParametersModel(): BodyParametersModel =
    BodyParametersModel(date, height, weight)