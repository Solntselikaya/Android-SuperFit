package com.example.superfit.presentation.exercise.components.success

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.common.TrainingType
import com.example.superfit.presentation.exercise.ExerciseEvent
import com.example.superfit.presentation.exercise.ExerciseViewModel
import com.example.superfit.presentation.ui.theme.DarkGray
import com.example.superfit.presentation.ui.theme.Purple
import com.example.superfit.presentation.ui.theme.White

@Composable
fun ExerciseSuccess(
    exercise: TrainingType,
    viewModel: ExerciseViewModel,
    navController: NavController
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(DarkGray)
    ) {
        Column(
            Modifier
                .padding(top = 80.dp)
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(exercise.title),
                modifier = Modifier
                    .padding(bottom = 64.dp)
                    .fillMaxWidth(),
                color = White,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )

            SuccessCircle()

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    viewModel.accept(
                        ExerciseEvent.OnGoHomeClick(navController)
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .navigationBarsPadding()
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Purple
                ),
                contentPadding = PaddingValues(vertical = 14.dp)
            ) {
                Text(
                    text = stringResource(R.string.go_home),
                    color = White,
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}

@Preview
@Composable
fun ExerciseSuccessPreview() {
    //ExerciseSuccess(TrainingType.PLANK, )
}