package com.example.superfit.presentation.exercise.plank

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
import com.example.superfit.presentation.exercise.plank.PlankEvent.InitScreen
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnAcceptStartAlertDialog
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnBackButtonClick
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnDismissErrorDialog
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnDismissStartAlertDialog
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnFinishClick
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnGoHomeClick
import com.example.superfit.presentation.exercise.plank.components.StartAlertDialog
import com.example.superfit.presentation.ui.theme.DarkGray
import com.example.superfit.presentation.ui.theme.Purple
import org.koin.androidx.compose.getViewModel

@Composable
fun PlankScreen(
    navController: NavController,
    viewModel: PlankViewModel = getViewModel(),
    exercise: TrainingType = TrainingType.PLANK
) {
    val state: ExerciseState by remember { viewModel.state }

    LaunchedEffect(Unit) {
        viewModel.accept(InitScreen(navController))
    }

    when (state) {
        Loading       -> {
            LoadingBar(color = Purple)
        }

        is Initial      -> {
            Box(modifier = Modifier
                .fillMaxSize()
                .background(DarkGray)) {
                StartAlertDialog(
                    totalCount = (state as Initial).totalCount ?: 0,
                    onAccept = {
                        viewModel.accept(
                            OnAcceptStartAlertDialog((state as Initial).totalCount ?: 0)
                        )
                    }
                ) { viewModel.accept(OnDismissStartAlertDialog) }
            }
        }

        is DoExercise   -> {
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
                    OnFinishClick(
                        (state as DoExercise).totalCount,
                        (state as DoExercise).currCount
                    )
                )
            }
        }

        is Success      -> {
            ExerciseSuccess(
                exercise = (state as Success).exercise
            ) { viewModel.accept(OnGoHomeClick) }
        }

        is UnSuccess    -> {
            ExerciseUnSuccess(
                exercise = (state as UnSuccess).exercise,
                missingCount = (state as UnSuccess).lessCount
            ) { viewModel.accept(OnGoHomeClick) }
        }
    }
}