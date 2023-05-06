package com.example.superfit.presentation.authorization.pin

sealed interface PINState {
    object Loading : PINState
    class InputPIN(val name: String, val pin: String) : PINState
    class Failure(val errorMessage: String) : PINState
}