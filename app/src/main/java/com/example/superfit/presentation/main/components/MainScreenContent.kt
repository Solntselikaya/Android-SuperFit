package com.example.superfit.presentation.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.common.TrainingType
import com.example.superfit.presentation.common.components.ExerciseCard
import com.example.superfit.presentation.main.MainEvent
import com.example.superfit.presentation.main.MainViewModel
import com.example.superfit.presentation.ui.theme.Black
import com.example.superfit.presentation.ui.theme.Gray

@Composable
fun MainScreenContent(
    navController: NavController,
    viewModel: MainViewModel,
    myWeight: Int?,
    myHeight: Int?,
    lastExercises: List<Pair<TrainingType, Int>>,
    modifier: Modifier
) {
    Text(
        text = stringResource(R.string.my_body),
        modifier = Modifier.padding(start = 16.dp),
        color = Black,
        style = MaterialTheme.typography.h5
    )

    MyBodyCard(
        myWeight,
        myHeight
    ) { viewModel.accept(MainEvent.OnMyBodyClick) }

    Row(
        Modifier
            .padding(start = 16.dp, end = 16.dp, top = 24.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.last_exercises),
            modifier = Modifier.align(Alignment.CenterVertically),
            color = Black,
            style = MaterialTheme.typography.h5
        )

        Text(
            text = stringResource(R.string.see_all),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable {
                    viewModel.accept(MainEvent.OnSeeAllExercisesClick)
                },
            color = Gray,
            style = MaterialTheme.typography.caption
        )
    }

    for (exercise in lastExercises) {
        ExerciseCard(
            image = painterResource(exercise.first.image),
            name = stringResource(exercise.first.title),
            description = stringResource(exercise.first.description)
        ) {
            viewModel.accept(
                MainEvent.OnExerciseCardClick(exercise.first)
            )
        }
    }

    Spacer(modifier = modifier)

    TextButton(
        onClick = { viewModel.accept(MainEvent.OnSignOutClick) },
        modifier = Modifier
            .padding(start = 16.dp, top = 16.dp)
            .navigationBarsPadding()
    ) {
        Icon(
            painter = painterResource(R.drawable.arrow_left),
            contentDescription = null,
            tint = Black
        )

        Text(
            text = stringResource(R.string.sign_out),
            modifier = Modifier,
            style = MaterialTheme.typography.h5,
            color = Black
        )
    }
}