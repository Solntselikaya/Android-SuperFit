package com.example.superfit.presentation.exercise.common.components.unsuccess

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.superfit.presentation.ui.theme.Purple
import com.example.superfit.presentation.ui.theme.White

@Composable
fun UnSuccessCircle(
    count: Int,
    unit: String,
    resultString: Int
) {
    Box {
        CircularProgressIndicator(
            progress = 1f,
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
                text = stringResource(resultString, count, unit),
                color = White,
                modifier = Modifier.width(141.dp),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
        }
    }
}