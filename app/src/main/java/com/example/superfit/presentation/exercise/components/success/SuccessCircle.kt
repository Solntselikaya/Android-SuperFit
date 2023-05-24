package com.example.superfit.presentation.exercise.components.success

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.Purple

@Composable
fun SuccessCircle() {
    Box {
        CircularProgressIndicator(
            progress = 1f,
            modifier = Modifier
                .size(216.dp),
            color = Purple,
            strokeWidth = 4.dp
        )

        Icon(
            painter = painterResource(R.drawable.round_check),
            contentDescription = null,
            modifier = Modifier.size(190.dp).align(Alignment.Center),
            tint = Purple
        )
    }
}

@Preview
@Composable
fun SuccessCirclePreview() {
    SuccessCircle()
}