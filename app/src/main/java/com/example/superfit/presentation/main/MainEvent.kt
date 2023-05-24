package com.example.superfit.presentation.main

import androidx.navigation.NavController
import com.example.superfit.common.TrainingType

sealed class MainEvent {
    class OnMyBodyClick(val navController: NavController) : MainEvent()
    class OnSeeAllExercisesClick(val navController: NavController) : MainEvent()
    class OnExerciseCardClick(
        val exercise: TrainingType,
        val navController: NavController
    ) : MainEvent()
    class OnSignOutClick(val navController: NavController) : MainEvent()
}