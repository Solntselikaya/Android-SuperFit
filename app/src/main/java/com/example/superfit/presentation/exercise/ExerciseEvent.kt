package com.example.superfit.presentation.exercise

import androidx.navigation.NavController

sealed class ExerciseEvent {
    class DoExerciseProcess(val count: Int) : ExerciseEvent()
    class OnBackButtonClick(
        val navController: NavController,
        val count: Int
    ) : ExerciseEvent()
    class OnFinishClick(
        val navController: NavController,
        val count: Int
    ) : ExerciseEvent()
    class OnGoHomeClick(val navController: NavController) : ExerciseEvent()
}
