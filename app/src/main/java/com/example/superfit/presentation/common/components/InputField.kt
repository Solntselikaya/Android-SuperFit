package com.example.superfit.presentation.common.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.superfit.presentation.ui.theme.TranslucentWhite
import com.example.superfit.presentation.ui.theme.White

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputField(
    value: String,
    placeHolderText: String,
    focusManager: FocusManager,
    imeAction: ImeAction,
    keyBoardType: KeyboardType = KeyboardType.Text,
    onValueChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    BasicTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier
            .padding(top = 34.dp, start = 50.dp, end = 50.dp)
            .fillMaxWidth(),
        enabled = true,
        textStyle = MaterialTheme.typography.subtitle1,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            },
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        singleLine = true,
        maxLines = 1,
        cursorBrush = SolidColor(White)
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