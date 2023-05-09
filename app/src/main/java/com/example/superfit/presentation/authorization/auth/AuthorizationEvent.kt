package com.example.superfit.presentation.authorization.auth

import androidx.navigation.NavController

sealed class AuthorizationEvent {
    class InputUserName(val userName: String): AuthorizationEvent()
    class SignInButtonClick(val navController: NavController, val name: String) : AuthorizationEvent()
    class SignUpButtonClick(val navController: NavController) : AuthorizationEvent()
}