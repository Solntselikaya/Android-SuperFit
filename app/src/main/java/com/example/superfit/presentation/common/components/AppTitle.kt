package com.example.superfit.presentation.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.White

@Composable
fun AppTitle(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.super_fit),
        modifier = modifier
            .padding(top = 68.dp),
        style = MaterialTheme.typography.h2,
        color = White
    )
}