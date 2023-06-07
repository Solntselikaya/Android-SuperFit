package com.example.superfit.presentation.mybody.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.AlertDialogGray
import com.example.superfit.presentation.ui.theme.LightGray
import com.example.superfit.presentation.ui.theme.Purple
import com.example.superfit.presentation.ui.theme.Transparent
import com.example.superfit.presentation.ui.theme.White

@Composable
fun ChangeStatAlertDialog(
    type: BodyParameter,
    value: String,
    onValueChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onAccept: () -> Unit
) {
    var label = ""
    var header = ""

    when (type) {
        BodyParameter.HEIGHT -> {
            label = stringResource(R.string.height)
            header = stringResource(R.string.change_your_height)
        }

        BodyParameter.WEIGHT -> {
            label = stringResource(R.string.weight)
            header = stringResource(R.string.change_your_weight)
        }
    }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onAccept() },
                modifier = Modifier.padding(end = 16.dp, start = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.change),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    color = Purple
                )
            }
        },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    color = Purple
                )
            }
        },
        title = {
            Text(
                text = header,
                modifier = Modifier,
                style = MaterialTheme.typography.body2,
                color = White
            )
        },
        text = {
            TextField(
                value = value,
                onValueChange = { onValueChange(it) },
                modifier = Modifier,
                textStyle = MaterialTheme.typography.button,
                label = {
                    Text(text = label, color = Purple)
                },
                placeholder = {
                    Text(text = stringResource(R.string.new_value))
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = White,
                    backgroundColor = Transparent,
                    disabledTextColor = White,
                    cursorColor = Purple,
                    focusedIndicatorColor = Purple,
                    unfocusedIndicatorColor = Purple,
                    focusedLabelColor = Purple,
                    unfocusedLabelColor = Purple,
                    placeholderColor = LightGray,
                    disabledPlaceholderColor = LightGray
                )
            )
        },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = AlertDialogGray
    )
}

@Preview
@Composable
fun InputDialogPreview() {
    ChangeStatAlertDialog(
        BodyParameter.WEIGHT,
        value = "",
        onValueChange = { /*TODO*/ },
        onDismiss = { /*TODO*/ }
    ) {}
}