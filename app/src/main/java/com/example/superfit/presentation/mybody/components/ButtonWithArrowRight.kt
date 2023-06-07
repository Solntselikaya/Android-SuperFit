package com.example.superfit.presentation.mybody.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.Transparent
import com.example.superfit.presentation.ui.theme.White

@Composable
fun ButtonWithArrowRight(
    text: Int,
    onClick: () -> Unit
) {
    TextButton(
        onClick = { onClick() },
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Transparent,
            contentColor = White
        )
    ) {
        Text(
            text = stringResource(text),
            modifier = Modifier,
            style = MaterialTheme.typography.h5,
            color = White
        )
        Icon(
            painter = painterResource(R.drawable.arrow_right),
            contentDescription = null,
            tint = White
        )
    }
}