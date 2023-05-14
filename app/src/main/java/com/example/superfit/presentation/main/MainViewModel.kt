package com.example.superfit.presentation.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.main.MainEvent.*

class MainViewModel(
): ViewModel() {

    private val _state: MutableState<MainState> =
        mutableStateOf(MainState.Initial)
    var state: State<MainState> = _state

    fun accept(event: MainEvent) {
        when(event) {
            is OnMyBodyClick          -> navigateToMyBodyScreen(event.navController)
            is OnSeeAllExercisesClick -> navigateToExercisesScreen(event.navController)
            is OnExerciseCardClick    -> navigateToExercise(event.exerciseName)
            is OnSignOutClick         -> signOut(event.navController)
        }
    }

    private fun navigateToMyBodyScreen(navController: NavController) {

    }

    private fun navigateToExercisesScreen(navController: NavController) {
        navController.navigate(Screen.ExercisesScreen.route)
    }

    private fun navigateToExercise(exerciseName: String) {

    }

    private fun signOut(navController: NavController) {

    }

}