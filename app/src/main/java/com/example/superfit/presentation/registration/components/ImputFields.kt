package com.example.superfit.presentation.registration.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.common.components.InputField
import com.example.superfit.presentation.registration.RegisterBody
import com.example.superfit.presentation.ui.theme.White

@Composable
fun InputFields(
    userName: String,
    email: String,
    code: String,
    repeatCode: String,
    onValueChanged: (RegisterBody) -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val focusManager = LocalFocusManager.current
        InputField(
            value = userName,
            placeHolderText = stringResource(R.string.user_name_input),
            focusManager = focusManager,
            imeAction = ImeAction.Next,
            onValueChanged = { onValueChanged(RegisterBody(userName = it)) }
        )

        InputField(
            value = email,
            placeHolderText = stringResource(R.string.email_input),
            focusManager = focusManager,
            imeAction = ImeAction.Next,
            keyBoardType = KeyboardType.Email,
            onValueChanged = { onValueChanged(RegisterBody(email = it)) }
        )

        InputField(
            value = code,
            placeHolderText = stringResource(R.string.code_input),
            focusManager = focusManager,
            imeAction = ImeAction.Next,
            keyBoardType = KeyboardType.Decimal,
            onValueChanged = { onValueChanged(RegisterBody(code = it)) }
        )

        InputField(
            value = repeatCode,
            placeHolderText = stringResource(R.string.repeat_code_input),
            focusManager = focusManager,
            imeAction = ImeAction.Done,
            keyBoardType = KeyboardType.Decimal,
            onValueChanged = { onValueChanged(RegisterBody(repeatCode = it)) }
        )

        TextButton(
            onClick = onSignUpButtonClick,
            modifier = modifier
                .wrapContentSize()
                .padding(top = 40.dp, bottom = 56.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent,
                contentColor = White
            )
        ) {
            Text(
                text = stringResource(R.string.sign_up),
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