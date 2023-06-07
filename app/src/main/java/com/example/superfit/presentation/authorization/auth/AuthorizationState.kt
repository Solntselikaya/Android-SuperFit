package com.example.superfit.presentation.authorization.auth

sealed class AuthorizationState {
    class InputUserName(val userName: String) : AuthorizationState()
}