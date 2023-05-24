package com.example.superfit.presentation.exercises.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superfit.common.TrainingType
import com.example.superfit.presentation.common.ExerciseCard

@Composable
fun ExercisesList(
    onClick: (TrainingType) -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .padding(bottom = 16.dp)
    ) {
        enumValues<TrainingType>().forEach { it ->
            ExerciseCard(
                image = painterResource(it.image),
                name = stringResource(it.title),
                description = stringResource(it.description)
            ) { onClick(it) }
        }
    }
}