package com.example.superfit.presentation.main

import androidx.navigation.NavController
import com.example.superfit.domain.model.TrainingType

sealed class MainEvent {
    class OnMyBodyClick(val navController: NavController) : MainEvent()
    class OnSeeAllExercisesClick(val navController: NavController) : MainEvent()
    class OnExerciseCardClick(
        val exercise: TrainingType,
        val count: Int,
        val navController: NavController
    ) : MainEvent()
    class OnSignOutClick(val navController: NavController) : MainEvent()
}