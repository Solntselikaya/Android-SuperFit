package com.example.superfit.presentation.authorization

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.common.InputField
import com.example.superfit.presentation.ui.theme.Transparent
import com.example.superfit.presentation.ui.theme.White

@Preview(showBackground = true)
@Composable
fun AuthorizationScreen() {
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

        UserNameInput(modifier = Modifier.align(Alignment.Center))

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
            Text(
                text = stringResource(R.string.sign_up),
                modifier = Modifier,
                style = MaterialTheme.typography.h5,
                color = White
            )
            Icon(
                painter = painterResource(R.drawable.arrow_forward),
                contentDescription = null,
                tint = White
            )
        }
    }
}

@Composable
fun UserNameInput(
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .wrapContentSize()
    ) {
        InputField(
            value = "",
            placeHolderText = stringResource(R.string.user_name_input),
            onValueChanged = { /*TODO*/ }
        )

        TextButton(
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = stringResource(R.string.sign_in),
                modifier = Modifier,
                style = MaterialTheme.typography.h5,
                color = White
            )
            Icon(
                painter = painterResource(R.drawable.arrow_forward),
                contentDescription = null,
                tint = White
            )
        }
    }
}