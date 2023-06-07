package com.example.superfit.presentation.exercise.running

import androidx.navigation.NavController

sealed class RunningEvent {
    class InitScreen(val navController: NavController) : RunningEvent()

    class OnFinishClick(
        val totalCount: Int,
        val currCount: Int
    ) : RunningEvent()

    object OnDismissErrorDialog : RunningEvent()

    object OnBackButtonClick : RunningEvent()

    object OnGoHomeClick : RunningEvent()
}
