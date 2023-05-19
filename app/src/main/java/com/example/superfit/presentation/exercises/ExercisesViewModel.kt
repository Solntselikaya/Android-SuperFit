package com.example.superfit.presentation.exercises

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.superfit.common.TrainingType
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.exercises.ExercisesEvent.*

class ExercisesViewModel: ViewModel() {

    fun accept(event: ExercisesEvent) {
        when(event) {
            is OnBackButtonClick   -> { navigateBack(event.navController) }
            is OnExerciseCardClick -> { navigateToExerciseScreen(event.navController, event.exercise) }
        }
    }

    private fun navigateBack(navController: NavController) {
        navController.popBackStack()
    }

    private fun navigateToExerciseScreen(navController: NavController, exercise: TrainingType) {
        navController.navigate(Screen.ExerciseScreen.passExerciseInfo(exercise.name))
    }
}