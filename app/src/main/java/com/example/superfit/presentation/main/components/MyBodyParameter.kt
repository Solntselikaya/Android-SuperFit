package com.example.superfit.presentation.main.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.example.superfit.presentation.ui.theme.Purple
import com.example.superfit.presentation.ui.theme.White

@Composable
fun MyBodyParameter(
    icon: Painter,
    value: String
) {
    Row(
        Modifier.wrapContentWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Purple
        )

        Text(
            text = value,
            color = White,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.subtitle1
        )
    }
}