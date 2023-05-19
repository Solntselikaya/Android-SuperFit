package com.example.superfit.presentation.exercise

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.common.Constants.EXERCISE_TYPE
import com.example.superfit.common.Constants.REPEATS
import com.example.superfit.common.TrainingType
import com.example.superfit.presentation.exercise.components.TrainingCircle
import com.example.superfit.presentation.ui.theme.DarkGray
import com.example.superfit.presentation.ui.theme.Purple
import com.example.superfit.presentation.ui.theme.White

@Composable
fun ExerciseScreen(
    arguments: Bundle,
    navController: NavController
) {
    //val viewModel = getViewModel<ExerciseViewModel>()

    val exerciseType = arguments.getString(EXERCISE_TYPE).toString()
    val exercise = TrainingType.valueOf(exerciseType)
    val count = arguments.getInt(REPEATS)

    Box(modifier = Modifier.fillMaxSize().background(DarkGray)) {
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 41.dp, start = 16.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = null,
                tint = White
            )
        }

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

            TrainingCircle(count = count, underText = stringResource(exercise.countText))

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /*TODO*/ },
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
                    text = stringResource(R.string.finish),
                    color = White,
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}