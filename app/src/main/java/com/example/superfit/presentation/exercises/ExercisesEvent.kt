package com.example.superfit.presentation.exercises

import androidx.navigation.NavController
import com.example.superfit.common.TrainingType

sealed class ExercisesEvent {
    class OnBackButtonClick(val navController: NavController): ExercisesEvent()
    class OnExerciseCardClick(
        val navController: NavController,
        val exercise: TrainingType
    ): ExercisesEvent()
}
