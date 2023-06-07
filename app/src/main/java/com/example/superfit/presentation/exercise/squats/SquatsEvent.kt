package com.example.superfit.presentation.exercise.squats

import androidx.navigation.NavController

sealed class SquatsEvent {
    class InitScreen(val navController: NavController) : SquatsEvent()

    class OnFinishClick(
        val totalCount: Int,
        val currCount: Int
    ) : SquatsEvent()

    object OnDismissErrorDialog : SquatsEvent()

    object OnBackButtonClick : SquatsEvent()

    object OnGoHomeClick : SquatsEvent()
}
