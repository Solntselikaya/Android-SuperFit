package com.example.superfit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.superfit.common.Constants.USER_NAME
import com.example.superfit.presentation.authorization.auth.AuthorizationScreen
import com.example.superfit.presentation.authorization.pin.PINScreen
import com.example.superfit.presentation.exercise.crunch.CrunchScreen
import com.example.superfit.presentation.exercise.plank.PlankScreen
import com.example.superfit.presentation.exercise.pushups.PushUpsScreen
import com.example.superfit.presentation.exercise.running.RunningScreen
import com.example.superfit.presentation.exercise.squats.SquatsScreen
import com.example.superfit.presentation.exercises.ExercisesScreen
import com.example.superfit.presentation.imagelist.ImageListScreen
import com.example.superfit.presentation.main.MainScreen
import com.example.superfit.presentation.mybody.MyBodyScreen
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

        composable(route = Screen.CrunchScreen.route) {
            CrunchScreen(navController = navController)
        }

        composable(route = Screen.SquatsScreen.route) {
            SquatsScreen(navController = navController)
        }

        composable(route = Screen.PushUpsScreen.route) {
            PushUpsScreen(navController = navController)
        }

        composable(route = Screen.PlankScreen.route) {
            PlankScreen(navController = navController)
        }

        composable(route = Screen.RunningScreen.route) {
            RunningScreen(navController = navController)
        }

        composable(route = Screen.MyBodyScreen.route) {
            MyBodyScreen(navController = navController)
        }

        composable(route = Screen.ImageListScreen.route) {
            ImageListScreen(navController = navController)
        }

    }
}