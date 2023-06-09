package com.example.superfit.presentation.authorization.pin

import androidx.navigation.NavController

//либо все с On либо все без
sealed class PINEvent {
    object OnDialogDismiss : PINEvent()
    class BackButtonClick(val navController: NavController) : PINEvent()
    class InputPINProcess(
        val navController: NavController,
        val name: String,
        val pin: String
    ) : PINEvent()
}