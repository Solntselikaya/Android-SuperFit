package com.example.superfit.navigation

sealed class Screen(val route: String) {
    object AuthorizationScreen : Screen("authorization_screen")
    object PINScreen : Screen("pin_screen/{$USER_NAME}") {
        fun passUserNameInfo(
            userName: String
        ) : String = "pin_screen/$userName"
    }
    object RegistrationScreen : Screen("registration_screen")
}