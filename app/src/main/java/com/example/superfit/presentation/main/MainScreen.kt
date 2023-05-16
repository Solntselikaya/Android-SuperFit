package com.example.superfit.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.domain.model.TrainingType
import com.example.superfit.presentation.common.ExerciseCard
import com.example.superfit.presentation.common.LoadingBar
import com.example.superfit.presentation.common.TopImage
import com.example.superfit.presentation.common.extensions.constrainSize
import com.example.superfit.presentation.common.extensions.onMeasureConstraints
import com.example.superfit.presentation.main.MainEvent.*
import com.example.superfit.presentation.main.MainState.Content
import com.example.superfit.presentation.main.MainState.Loading
import com.example.superfit.presentation.main.components.MyBodyCard
import com.example.superfit.presentation.ui.theme.Black
import com.example.superfit.presentation.ui.theme.Gray
import com.example.superfit.presentation.ui.theme.White
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(navController: NavController) {
    val viewModel = getViewModel<MainViewModel>()

    val state: MainState by remember { viewModel.state }

    var constraints by remember { mutableStateOf(Constraints()) }
    Column(
        Modifier
            .fillMaxSize()
            .background(White)
            .onMeasureConstraints { constraints = it }
            .verticalScroll(rememberScrollState())
    ) {
        TopImage(false) {}

        when (state) {
            Loading    -> LoadingBar(
                Modifier.constrainSize { constraints }.navigationBarsPadding(),
                Black
            )
            is Content -> MainScreenContent(
                navController,
                viewModel,
                (state as Content).myWeight,
                (state as Content).myHeight,
                (state as Content).lastExercises,
                Modifier.weight(1f)
            )
        }
    }
}

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
    ) { viewModel.accept(OnMyBodyClick(navController)) }

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
                    viewModel.accept(OnSeeAllExercisesClick(navController))
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
        ) { viewModel.accept(
            OnExerciseCardClick(exercise.first, exercise.second, navController)
        ) }
    }

    Spacer(modifier = modifier)

    TextButton(
        onClick = { viewModel.accept(OnSignOutClick(navController)) },
        modifier = Modifier
            .padding(start = 16.dp)
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