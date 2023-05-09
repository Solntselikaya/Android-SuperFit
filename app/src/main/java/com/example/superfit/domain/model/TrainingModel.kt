package com.example.superfit.domain.model

import com.example.superfit.data.dto.TrainingDto

data class TrainingModel(
    val date: String,
    val exercise: TrainingType,
    val repeatCount: Int
)

fun TrainingModel.toTrainingDto(): TrainingDto =
    TrainingDto(
        date = date,
        exercise = exercise.name,
        repeatCount = repeatCount
    )