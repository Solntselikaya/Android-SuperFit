package com.example.superfit.presentation.exercise.pushups

import androidx.navigation.NavController

sealed class PushUpsEvent {
    class InitScreen(val navController: NavController) : PushUpsEvent()

    class OnFinishClick(
        val totalCount: Int,
        val currCount: Int
    ) : PushUpsEvent()

    object OnDismissErrorDialog : PushUpsEvent()

    object OnBackButtonClick : PushUpsEvent()

    object OnGoHomeClick : PushUpsEvent()
}
