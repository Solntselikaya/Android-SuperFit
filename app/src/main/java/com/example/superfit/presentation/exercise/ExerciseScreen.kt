package com.example.superfit.presentation.exercise

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.superfit.common.Constants.EXERCISE_TYPE
import com.example.superfit.common.TrainingType
import com.example.superfit.presentation.common.LoadingBar
import com.example.superfit.presentation.ui.theme.Purple
import org.koin.androidx.compose.getViewModel
import com.example.superfit.presentation.exercise.ExerciseState.*
import com.example.superfit.presentation.exercise.components.ExerciseContent

@Composable
fun ExerciseScreen(
    arguments: Bundle,
    navController: NavController
) {
    val viewModel = getViewModel<ExerciseViewModel>()
    val state: ExerciseState by remember { viewModel.state }

    val exerciseType = arguments.getString(EXERCISE_TYPE).toString()
    val exercise = TrainingType.valueOf(exerciseType)

    when(state) {
        Initial       -> { viewModel.getCurrCount(exercise) }
        Loading       -> { LoadingBar(color = Purple) }
        is DoExercise -> { ExerciseContent(
            exercise = exercise,
            count = (state as DoExercise).count,
            viewModel = viewModel,
            navController = navController) }
        is Success    -> {  }
        is UnSuccess  -> {  }
    }
}