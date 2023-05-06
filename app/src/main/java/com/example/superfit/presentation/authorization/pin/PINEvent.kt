package com.example.superfit.presentation.authorization.pin

import androidx.navigation.NavController

interface PINEvent {
    class BackButtonClick(val navController: NavController) : PINEvent
    class InputPIN(
        val navController: NavController,
        val name: String,
        val pin: String
        ): PINEvent
}