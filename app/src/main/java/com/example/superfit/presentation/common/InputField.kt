package com.example.superfit.presentation.common

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.superfit.presentation.ui.theme.TranslucentWhite
import com.example.superfit.presentation.ui.theme.Transparent
import com.example.superfit.presentation.ui.theme.White

@Composable
fun InputField(
    value: String,
    placeHolderText: String,
    onValueChanged: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier,
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
    )
}