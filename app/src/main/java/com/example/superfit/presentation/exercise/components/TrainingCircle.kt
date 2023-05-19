package com.example.superfit.presentation.exercise.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.presentation.ui.theme.Purple
import com.example.superfit.presentation.ui.theme.White

@Composable
fun TrainingCircle(
    count: Int,
    underText: String,
    progress: Float = 1f
) {
    Box {
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier
                .size(216.dp),
            color = Purple,
            strokeWidth = 4.dp
        )

        Column(
            Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = count.toString(),
                color = White,
                style = MaterialTheme.typography.h2,
                textAlign = TextAlign.Center
            )
            Text(
                text = underText,
                color = White,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrainingCirclePreview() {
    TrainingCircle(25, "NEED TO DO")
}