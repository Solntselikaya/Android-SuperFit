package com.example.superfit.presentation.exercises

import androidx.navigation.NavController
import com.example.superfit.common.TrainingType

sealed class ExercisesEvent {
    object OnBackButtonClick : ExercisesEvent()
    class InitScreen(val navController: NavController) : ExercisesEvent()
    class OnExerciseCardClick(val exercise: TrainingType) : ExercisesEvent()
}
