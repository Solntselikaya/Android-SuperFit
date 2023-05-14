package com.example.superfit.presentation.main

import androidx.navigation.NavController

sealed class MainEvent {
    class OnMyBodyClick(val navController: NavController) : MainEvent()
    class OnSeeAllExercisesClick(val navController: NavController) : MainEvent()
    class OnExerciseCardClick(
        val exerciseName: String,
        val navController: NavController
    ) : MainEvent()
    class OnSignOutClick(val navController: NavController) : MainEvent()
}