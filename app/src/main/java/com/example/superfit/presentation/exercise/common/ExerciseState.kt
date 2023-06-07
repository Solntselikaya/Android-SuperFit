package com.example.superfit.presentation.exercise.common

import com.example.superfit.common.TrainingType

sealed class ExerciseState {
    object Loading : ExerciseState()

    class Initial(val totalCount: Int? = null) : ExerciseState()

    data class DoExercise(
        val totalCount: Int,
        val currCount: Int,
        val error: Int? = null
    ) : ExerciseState()

    data class Success(val exercise: TrainingType) : ExerciseState()

    data class UnSuccess(
        val exercise: TrainingType,
        val lessCount: Int
    ) : ExerciseState()
}
