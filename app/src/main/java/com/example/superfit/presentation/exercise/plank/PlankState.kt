package com.example.superfit.presentation.exercise.plank

import com.example.superfit.common.TrainingType

sealed class PlankState {
    object Loading : PlankState()

    class Initial(val totalCount: Int) : PlankState()

    data class DoExercise(
        val totalCount: Int,
        val currCount: Int,
        val error: Int? = null
    ) : PlankState()

    data class Success(val exercise: TrainingType) : PlankState()

    data class UnSuccess(
        val exercise: TrainingType,
        val lessCount: Int
    ) : PlankState()
}
