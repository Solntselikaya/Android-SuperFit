package com.example.superfit.presentation.exercises

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.superfit.common.TrainingType
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.exercises.ExercisesEvent.InitScreen
import com.example.superfit.presentation.exercises.ExercisesEvent.OnBackButtonClick
import com.example.superfit.presentation.exercises.ExercisesEvent.OnExerciseCardClick

class ExercisesViewModel : ViewModel() {

    private lateinit var navController: NavController
    fun accept(event: ExercisesEvent) {
        when (event) {
            OnBackButtonClick      -> navigateBack()
            is InitScreen          -> navController = event.navController
            is OnExerciseCardClick -> navigateToExerciseScreen(event.exercise)
        }
    }

    private fun navigateBack() {
        navController.navigate(Screen.MainScreen.route) {
            popUpTo(Screen.MainScreen.route) {
                inclusive = true
            }
        }
    }

    private fun navigateToExerciseScreen(exercise: TrainingType) {
        val screen = when (exercise) {
            TrainingType.CRUNCH  -> Screen.CrunchScreen.route
            TrainingType.SQUATS  -> Screen.SquatsScreen.route
            TrainingType.PUSH_UP -> Screen.PushUpsScreen.route
            TrainingType.PLANK   -> Screen.PlankScreen.route
            TrainingType.RUNNING -> Screen.RunningScreen.route
        }
        navController.navigate(screen)
    }
}