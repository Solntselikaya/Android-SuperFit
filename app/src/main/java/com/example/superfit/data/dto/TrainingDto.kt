package com.example.superfit.data.dto

import com.example.superfit.domain.model.TrainingModel
import com.example.superfit.common.TrainingType

data class TrainingDto(
    val date: String,
    val exercise: String,
    val repeatCount: Int
)

fun TrainingDto.toTrainingModel(): TrainingModel {
    val exerciseType = when (exercise) {
        TrainingType.CRUNCH.name  -> TrainingType.CRUNCH
        TrainingType.PLANK.name   -> TrainingType.PLANK
        TrainingType.PUSH_UP.name -> TrainingType.PUSH_UP
        TrainingType.RUNNING.name -> TrainingType.RUNNING
        else                      -> TrainingType.SQUATS
    }

    return TrainingModel(
        date = date,
        exercise = exerciseType,
        repeatCount = repeatCount
    )
}