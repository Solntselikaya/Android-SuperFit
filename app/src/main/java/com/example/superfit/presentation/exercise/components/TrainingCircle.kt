package com.example.superfit.presentation.exercise.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.common.TrainingType
import com.example.superfit.presentation.ui.theme.Purple
import com.example.superfit.presentation.ui.theme.White
import kotlinx.coroutines.delay
import kotlin.time.ExperimentalTime
import kotlin.time.seconds

@Composable
fun TrainingCircle(
    exercise: TrainingType,
    totalCount: Int,
    currCount: Int = 0,
    progress: Float = 1f
) {
    Box {
        if (exercise != TrainingType.CRUNCH) {
            CircularProgressIndicator(
                progress = animateFloatAsState(
                    targetValue = (currCount.toFloat() / totalCount.toFloat()),
                    animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
                ).value,
                modifier = Modifier
                    .size(216.dp),
                color = Purple,
                strokeWidth = 4.dp
            )
        } else {
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .size(216.dp),
                color = Purple,
                strokeWidth = 4.dp
            )
        }


        Column(
            Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = (totalCount - currCount).toString(),
                color = White,
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(exercise.countText),
                color = White,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalTime::class)
@Preview(showBackground = true)
@Composable
fun TrainingCirclePreview() {
    val seconds = 25
    var currCount by remember { mutableStateOf(0) }
    TrainingCircle(TrainingType.PUSH_UP, 25, currCount)
    LaunchedEffect(Unit) {
        while(currCount < seconds) {
            delay(1.seconds)
            currCount += 1
        }
    }

}