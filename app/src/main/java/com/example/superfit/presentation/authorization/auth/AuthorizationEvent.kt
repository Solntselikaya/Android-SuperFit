package com.example.superfit.presentation.authorization.auth

import androidx.navigation.NavController

sealed class AuthorizationEvent {
    object OnDialogDismiss : AuthorizationEvent()
    class InputUserNameProcess(val userName: String): AuthorizationEvent()
    class SignInButtonClick(val navController: NavController, val name: String) : AuthorizationEvent()
    class SignUpButtonClick(val navController: NavController) : AuthorizationEvent()
}