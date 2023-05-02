package com.example.superfit.presentation.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.common.InputField
import com.example.superfit.presentation.ui.theme.White

@Composable
fun RegistrationScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.super_fit),
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 68.dp),
            style = MaterialTheme.typography.h2,
            color = White
        )

        InputFields(modifier = Modifier.align(Alignment.Center))

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentSize()
                .padding(bottom = 56.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Transparent,
                contentColor = White
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_left),
                contentDescription = null,
                tint = White
            )
            Text(
                text = stringResource(R.string.sign_in),
                modifier = Modifier,
                style = MaterialTheme.typography.h5,
                color = White
            )
        }
    }
}

@Composable
fun InputFields(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputField(
            value = "",
            placeHolderText = stringResource(R.string.user_name_input),
            onValueChanged = { /*TODO*/ }
        )

        InputField(
            value = "",
            placeHolderText = stringResource(R.string.email_input),
            onValueChanged = { /*TODO*/ }
        )

        InputField(
            value = "",
            placeHolderText = stringResource(R.string.code_input),
            onValueChanged = { /*TODO*/ }
        )

        InputField(
            value = "",
            placeHolderText = stringResource(R.string.repeat_code_input),
            onValueChanged = { /*TODO*/ }
        )

        TextButton(
            onClick = { /*TODO*/ },
            modifier = modifier
                .wrapContentSize()
                .padding(top = 40.dp, bottom = 56.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Transparent,
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