package com.example.superfit.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.superfit.presentation.ui.theme.White
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingBar(
    modifier: Modifier = Modifier,
    color: Color = White
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = color
        )
    }
}