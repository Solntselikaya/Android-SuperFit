package com.example.superfit.presentation.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.common.AppTitle
import com.example.superfit.presentation.common.ErrorDialog
import com.example.superfit.presentation.common.LoadingBar
import com.example.superfit.presentation.registration.RegistrationEvent.*
import com.example.superfit.presentation.registration.RegistrationState.*
import com.example.superfit.presentation.registration.components.InputFields
import com.example.superfit.presentation.ui.theme.White
import org.koin.androidx.compose.getViewModel

@Composable
fun RegistrationScreen(
    navController: NavController
) {
    val viewModel = getViewModel<RegistrationViewModel>()

    val state: RegistrationState by remember {viewModel.state }
    val error: List<Int> by remember { viewModel.error }

    ErrorDialog(error = error) { viewModel.accept(OnDialogDismiss) }

    when(state) {
        Loading      -> { LoadingBar(Modifier.fillMaxSize()) }
        is InputInfo -> { RegistrationScreenContent(navController, state, viewModel) }
    }
}

@Composable
fun RegistrationScreenContent(
    navController: NavController,
    state: RegistrationState,
    viewModel: RegistrationViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppTitle()

        InputFields(
            userName = (state as InputInfo).data.userName ?: "",
            email = state.data.email ?: "",
            code = state.data.code ?: "",
            repeatCode = state.data.repeatCode ?: "",
            { viewModel.accept(InputInfoProcess(it)) },
            { viewModel.accept(SignUpButtonClick(navController)) }
        )

        TextButton(
            onClick = {
                navController.popBackStack(Screen.AuthorizationScreen.route, false)
            },
            modifier = Modifier
                .padding(bottom = 56.dp)
                .wrapContentSize(),
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