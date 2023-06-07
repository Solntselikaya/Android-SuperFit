package com.example.superfit.navigation

import com.example.superfit.common.Constants.USER_NAME

sealed class Screen(val route: String) {
    object AuthorizationScreen : Screen("authorization_screen")
    object PINScreen : Screen("pin_screen/{$USER_NAME}") {
        fun passUserNameInfo(
            userName: String
        ): String = "pin_screen/$userName"
    }

    object RegistrationScreen : Screen("registration_screen")
    object MainScreen : Screen("main_screen")
    object ExercisesScreen : Screen("exercises_screen")
    object CrunchScreen : Screen("crunch_screen")
    object SquatsScreen : Screen("squats_screen")
    object PushUpsScreen : Screen("push_ups_screen")
    object PlankScreen : Screen("plank_screen")
    object RunningScreen : Screen("running_screen")

    object MyBodyScreen : Screen("my_body_screen")

    object ImageListScreen : Screen("image_list_screen")
}