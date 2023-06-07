package com.example.superfit.presentation.exercise.running

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.superfit.common.TrainingType
import com.example.superfit.presentation.common.components.ErrorDialog
import com.example.superfit.presentation.common.components.LoadingBar
import com.example.superfit.presentation.exercise.common.ExerciseState
import com.example.superfit.presentation.exercise.common.ExerciseState.DoExercise
import com.example.superfit.presentation.exercise.common.ExerciseState.Initial
import com.example.superfit.presentation.exercise.common.ExerciseState.Loading
import com.example.superfit.presentation.exercise.common.ExerciseState.Success
import com.example.superfit.presentation.exercise.common.ExerciseState.UnSuccess
import com.example.superfit.presentation.exercise.common.components.ExerciseContent
import com.example.superfit.presentation.exercise.common.components.success.ExerciseSuccess
import com.example.superfit.presentation.exercise.common.components.unsuccess.ExerciseUnSuccess
import com.example.superfit.presentation.exercise.running.RunningEvent.InitScreen
import com.example.superfit.presentation.exercise.running.RunningEvent.OnBackButtonClick
import com.example.superfit.presentation.exercise.running.RunningEvent.OnDismissErrorDialog
import com.example.superfit.presentation.exercise.running.RunningEvent.OnFinishClick
import com.example.superfit.presentation.exercise.running.RunningEvent.OnGoHomeClick
import com.example.superfit.presentation.ui.theme.Purple
import org.koin.androidx.compose.getViewModel

@Composable
fun RunningScreen(
    navController: NavController,
    viewModel: RunningViewModel = getViewModel(),
    exercise: TrainingType = TrainingType.RUNNING
) {
    val state: ExerciseState by remember { viewModel.state }

    LaunchedEffect(Unit) {
        viewModel.accept(InitScreen(navController))
    }

    when (state) {
        Loading -> {
            LoadingBar(color = Purple)
        }

        is Initial -> Unit
        is DoExercise -> {
            val err = (state as DoExercise).error
            if (err != null) {
                ErrorDialog(error = listOf(err)) { viewModel.accept(OnDismissErrorDialog) }
            }
            ExerciseContent(
                exercise = exercise,
                totalCount = (state as DoExercise).totalCount,
                currCount = (state as DoExercise).currCount,
                { viewModel.accept(OnBackButtonClick) }
            ) {
                viewModel.accept(
                    OnFinishClick((state as DoExercise).totalCount, (state as DoExercise).currCount)
                )
            }
        }

        is Success -> {
            ExerciseSuccess(
                exercise = (state as Success).exercise
            ) { viewModel.accept(OnGoHomeClick) }
        }

        is UnSuccess -> {
            ExerciseUnSuccess(
                exercise = (state as UnSuccess).exercise,
                missingCount = (state as UnSuccess).lessCount
            ) { viewModel.accept(OnGoHomeClick) }
        }
    }
}