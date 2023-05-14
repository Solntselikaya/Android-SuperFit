package com.example.superfit.presentation.main

sealed class MainState {
    object Initial : MainState()
    object Loading : MainState()
    class Failure(val errorMessage: String) : MainState()
}
