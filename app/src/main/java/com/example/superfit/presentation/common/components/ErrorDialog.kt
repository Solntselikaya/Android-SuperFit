package com.example.superfit.presentation.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.AlertDialogGray
import com.example.superfit.presentation.ui.theme.DarkGray
import com.example.superfit.presentation.ui.theme.TranslucentWhite70
import com.example.superfit.presentation.ui.theme.White

@Composable
fun ErrorDialog(
    error: List<Int>,
    onDismiss: () -> Unit
) {
    if (error.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = AlertDialogGray,
            title = {
                Text(
                    text = stringResource(R.string.error),
                    style = MaterialTheme.typography.body2,
                    color = White
                )
            },
            text = {
                var errorStr = ""
                error.forEachIndexed { index, element ->
                    errorStr += stringResource(element)
                    if (index != error.lastIndex) {
                        errorStr += "\n"
                    }
                }

                Text(
                    text = errorStr,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Normal,
                    color = TranslucentWhite70
                )
            },
            buttons = {}
        )
    }
}

@Preview
@Composable
fun ErrorDialogPreview() {
    ErrorDialog(listOf(R.string.error)) {}
}