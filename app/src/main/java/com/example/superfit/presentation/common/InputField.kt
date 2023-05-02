package com.example.superfit.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.superfit.presentation.ui.theme.TranslucentWhite
import com.example.superfit.presentation.ui.theme.Transparent
import com.example.superfit.presentation.ui.theme.White

@Composable
fun InputField(
    value: String,
    placeHolderText: String,
    onValueChanged: (String) -> Unit
) {
    /*TextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier
            .padding(top = 24.dp, start = 50.dp, end = 50.dp)
            .fillMaxWidth(),
        textStyle = MaterialTheme.typography.subtitle1,
        placeholder = {
            Text(
                text = placeHolderText,
                style = MaterialTheme.typography.subtitle1,
                color = White
            )
        },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = White,
            placeholderColor = White,
            cursorColor = White,
            focusedIndicatorColor = TranslucentWhite,
            unfocusedIndicatorColor = TranslucentWhite,
            backgroundColor = Transparent
        )
    )*/

    BasicTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier
            .padding(top = 34.dp, start = 50.dp, end = 50.dp)
            .fillMaxWidth(),
        enabled = true,
        singleLine = true,
        maxLines = 1,
        cursorBrush = SolidColor(White),
        textStyle = MaterialTheme.typography.subtitle1,
    ) { innerTextField ->
        Box(
            Modifier
                .fillMaxWidth()
                .drawWithContent {
                    drawContent()
                    drawLine(
                        color = TranslucentWhite,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = 3f
                    )
                }
                .padding(start = 4.dp, bottom = 4.dp)
        ) {
            innerTextField()

            if (value.isEmpty()) {
                Text(
                    text = placeHolderText,
                    modifier = Modifier.padding(bottom = 0.dp),
                    style = MaterialTheme.typography.subtitle1,
                    color = White
                )
            }
        }
    }
}