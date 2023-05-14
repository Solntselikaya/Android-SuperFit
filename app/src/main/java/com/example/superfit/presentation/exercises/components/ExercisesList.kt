package com.example.superfit.presentation.exercises.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.superfit.domain.model.TrainingType
import com.example.superfit.presentation.common.ExerciseCard

@Composable
fun ExercisesList(
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding()
    ) {
        enumValues<TrainingType>().forEach { it ->
            ExerciseCard(
                image = painterResource(it.image),
                name = stringResource(it.title),
                description = stringResource(it.description)
            ) { onClick() }
        }
    }
}