package com.example.superfit.presentation.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.common.TopImage
import com.example.superfit.presentation.exercises.components.ExercisesList
import com.example.superfit.presentation.ui.theme.Black
import com.example.superfit.presentation.ui.theme.White
import org.koin.androidx.compose.getViewModel
import com.example.superfit.presentation.exercises.ExercisesEvent.*

@Composable
fun ExercisesScreen(navController: NavController) {
    val viewModel = getViewModel<ExercisesViewModel>()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        TopImage(true) { viewModel.accept(OnBackButtonClick(navController)) }

        Text(
            text = stringResource(R.string.exercises),
            modifier = Modifier.padding(start = 16.dp),
            color = Black,
            style = MaterialTheme.typography.h5
        )

        ExercisesList() { viewModel.accept(OnExerciseCardClick(navController, it)) }
    }
}