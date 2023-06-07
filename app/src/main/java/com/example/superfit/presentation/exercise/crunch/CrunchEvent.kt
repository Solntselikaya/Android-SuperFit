package com.example.superfit.presentation.exercise.crunch

import androidx.navigation.NavController

sealed class CrunchEvent {

    class InitScreen(val navController: NavController) : CrunchEvent()

    object OnDismissErrorDialog : CrunchEvent()

    class OnFinishClick(val totalCount: Int, val error: Int? = null) : CrunchEvent()

    object OnBackButtonClick : CrunchEvent()

}
