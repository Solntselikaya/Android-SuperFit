package com.example.superfit.presentation.authorization.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.authorization.auth.AuthorizationEvent.*
import com.example.superfit.presentation.common.AppTitle
import com.example.superfit.presentation.common.InputField
import com.example.superfit.presentation.ui.theme.Transparent
import com.example.superfit.presentation.ui.theme.White

@Composable
fun AuthorizationScreen(
    navController: NavController,
    viewModel: AuthorizationViewModel = viewModel()
) {
    val state: AuthorizationState by remember { viewModel.state }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        AppTitle(Modifier.align(Alignment.TopCenter))

        when(state) {
            is AuthorizationState.InputUserName -> {
                UserNameInput(
                    modifier = Modifier.align(Alignment.Center),
                    value = (state as AuthorizationState.InputUserName).userName,
                    { viewModel.accept(SignInButtonClick(navController, it)) }
                ) { viewModel.accept(InputUserName(it)) }
            }
        }

        TextButton(
            onClick = {
                viewModel.accept(SignUpButtonClick(navController))
            },
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
                painter = painterResource(R.drawable.arrow_right),
                contentDescription = null,
                tint = White
            )
        }
    }

}

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
        InputField(
            value = value,
            placeHolderText = stringResource(R.string.user_name_input),
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