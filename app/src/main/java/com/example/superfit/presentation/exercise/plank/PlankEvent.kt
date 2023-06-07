package com.example.superfit.presentation.exercise.plank

import androidx.navigation.NavController

sealed class PlankEvent {
    class InitScreen(val navController: NavController) : PlankEvent()

    object OnDismissStartAlertDialog : PlankEvent()

    class OnAcceptStartAlertDialog(val totalCount: Int) : PlankEvent()

    object OnDismissErrorDialog : PlankEvent()

    object OnBackButtonClick : PlankEvent()

    class OnFinishClick(val totalCount: Int, val currCount: Int) : PlankEvent()

    object OnGoHomeClick : PlankEvent()
}
