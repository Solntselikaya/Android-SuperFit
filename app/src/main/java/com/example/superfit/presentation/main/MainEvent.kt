package com.example.superfit.presentation.main

import androidx.navigation.NavController
import com.example.superfit.common.TrainingType

sealed class MainEvent {
    class InitScreen(val navController: NavController) : MainEvent()

    object OnDismissErrorDialog : MainEvent()

    object OnMyBodyClick : MainEvent()

    object OnSeeAllExercisesClick : MainEvent()

    class OnExerciseCardClick(val exercise: TrainingType) : MainEvent()

    object OnSignOutClick : MainEvent()
}