package com.example.superfit.presentation.authorization.auth

sealed interface AuthorizationState {
    class InputUserName(val userName: String) : AuthorizationState
}