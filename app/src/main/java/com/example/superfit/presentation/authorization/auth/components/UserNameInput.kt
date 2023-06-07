package com.example.superfit.presentation.authorization.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.common.components.InputField
import com.example.superfit.presentation.ui.theme.White


@Composable
fun UserNameInput(
    modifier: Modifier,
    value: String,
    onSignUpButtonClick: (String) -> Unit,
    onValueChanged: (String) -> Unit
) {
    Column(
        modifier = modifier
            .wrapContentSize()
    ) {
        val focusManager = LocalFocusManager.current
        InputField(
            value = value,
            placeHolderText = stringResource(R.string.email_input),
            focusManager = focusManager,
            imeAction = ImeAction.Done,
            keyBoardType = KeyboardType.Email,
            onValueChanged = { onValueChanged(it) }
        )

        TextButton(
            onClick = { onSignUpButtonClick(value) },
            modifier = Modifier.padding(start = 50.dp)
        ) {
            Text(
                text = stringResource(R.string.sign_in),
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
}