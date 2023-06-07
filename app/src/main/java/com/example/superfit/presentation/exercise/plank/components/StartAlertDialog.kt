package com.example.superfit.presentation.exercise.plank.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.AlertDialogGray
import com.example.superfit.presentation.ui.theme.DarkGray
import com.example.superfit.presentation.ui.theme.Purple
import com.example.superfit.presentation.ui.theme.TranslucentWhite70
import com.example.superfit.presentation.ui.theme.White

@Composable
fun StartAlertDialog(
    totalCount: Int,
    onAccept: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onAccept() },
                modifier = Modifier.padding(end = 16.dp, start = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.go),
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
                    text = stringResource(R.string.later),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Medium,
                    color = Purple
                )
            }
        },
        title = {
            Text(
                text = stringResource(R.string.start_the_training),
                style = MaterialTheme.typography.body2,
                color = White
            )
        },
        text = {
            Text(
                text = stringResource(R.string.start_alert_dialog, totalCount),
                color = TranslucentWhite70,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Normal
            )
        },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = AlertDialogGray
    )
}

@Preview(showBackground = true)
@Composable
fun StartAlertDialogPreview() {
    StartAlertDialog(totalCount = 50, onAccept = {}) {}
}