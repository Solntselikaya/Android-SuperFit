package com.example.superfit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.superfit.common.Constants.EXERCISE_TYPE
import com.example.superfit.common.Constants.REPEATS
import com.example.superfit.common.Constants.USER_NAME
import com.example.superfit.presentation.authorization.auth.AuthorizationScreen
import com.example.superfit.presentation.authorization.pin.PINScreen
import com.example.superfit.presentation.exercise.ExerciseScreen
import com.example.superfit.presentation.exercises.ExercisesScreen
import com.example.superfit.presentation.main.MainScreen
import com.example.superfit.presentation.registration.RegistrationScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.AuthorizationScreen.route
    ) {

        composable(route = Screen.AuthorizationScreen.route) {
            AuthorizationScreen(navController = navController)
        }

        composable(
            route = Screen.PINScreen.route,
            arguments = listOf(
                navArgument(USER_NAME) {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.let { it1 -> PINScreen(it1, navController = navController) }
        }

        composable(route = Screen.RegistrationScreen.route) {
            RegistrationScreen(navController = navController)
        }

        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(route = Screen.ExercisesScreen.route) {
            ExercisesScreen(navController = navController)
        }

        composable(
            route = Screen.ExerciseScreen.route,
            arguments = listOf(
                navArgument(EXERCISE_TYPE) {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.let { it1 ->
                ExerciseScreen(arguments = it1, navController)
            }
        }

    }
}