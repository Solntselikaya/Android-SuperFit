package com.example.superfit.presentation.main

import com.example.superfit.common.TrainingType

sealed class MainState {
    object Loading : MainState()

    data class Content(
        val myWeight: Int?,
        val myHeight: Int?,
        val lastExercises: List<Pair<TrainingType, Int>>,
        val error: Int? = null
    ) : MainState()
}
