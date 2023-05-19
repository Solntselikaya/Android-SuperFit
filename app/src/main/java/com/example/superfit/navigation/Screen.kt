package com.example.superfit.navigation

import com.example.superfit.common.Constants.EXERCISE_TYPE
import com.example.superfit.common.Constants.USER_NAME
import com.example.superfit.common.TrainingType

sealed class Screen(val route: String) {
    object AuthorizationScreen : Screen("authorization_screen")
    object PINScreen : Screen("pin_screen/{$USER_NAME}") {
        fun passUserNameInfo(
            userName: String
        ) : String = "pin_screen/$userName"
    }
    object RegistrationScreen : Screen("registration_screen")
    object MainScreen: Screen("main_screen")
    object ExercisesScreen: Screen("exercises_screen")

    object ExerciseScreen: Screen("exercise_screen/{$EXERCISE_TYPE}") {
        fun passExerciseInfo(
            exerciseType: String
        ): String = "exercise_screen/$exerciseType"
    }
}