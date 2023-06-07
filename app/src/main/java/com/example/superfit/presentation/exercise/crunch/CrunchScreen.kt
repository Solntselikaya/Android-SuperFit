package com.example.superfit.presentation.exercise.crunch

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
import com.example.superfit.presentation.exercise.crunch.CrunchEvent.InitScreen
import com.example.superfit.presentation.exercise.crunch.CrunchEvent.OnBackButtonClick
import com.example.superfit.presentation.exercise.crunch.CrunchEvent.OnDismissErrorDialog
import com.example.superfit.presentation.exercise.crunch.CrunchEvent.OnFinishClick
import com.example.superfit.presentation.ui.theme.Purple
import org.koin.androidx.compose.getViewModel

@Composable
fun CrunchScreen(
    navController: NavController,
    viewModel: CrunchViewModel = getViewModel(),
    exercise: TrainingType = TrainingType.CRUNCH
) {
    val state: ExerciseState by remember { viewModel.state }

    LaunchedEffect(Unit) {
        viewModel.accept(InitScreen(navController))
    }

    when (state) {
        Loading       -> { LoadingBar(color = Purple) }
        is Initial    -> Unit
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
            ) { viewModel.accept(OnFinishClick((state as DoExercise).totalCount)) }
        }
        is Success -> Unit
        is UnSuccess -> Unit
    }
}