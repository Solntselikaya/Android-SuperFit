package com.example.superfit.presentation.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.common.AppTitle
import com.example.superfit.presentation.registration.RegistrationEvent.*
import com.example.superfit.presentation.registration.components.InputFields
import com.example.superfit.presentation.ui.theme.Black
import com.example.superfit.presentation.ui.theme.White

@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = viewModel()
) {
    val state: RegistrationState by remember {viewModel.state }
    val error: List<Int> by remember { viewModel.errorMessage }

    if (error.isNotEmpty()) {
        
        AlertDialog(
            onDismissRequest = { viewModel.accept(OnDialogDismiss) },
            title = { Text(text = "Ошибка") },
            text = {
                var errorStr = ""
                for (element in error) {
                    errorStr += stringResource(element)
                    errorStr += "\n"
                }
                
                Text(text = errorStr, color = Black)
            },
            buttons = {}
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppTitle()

        InputFields(
            userName = (state as RegistrationState.InputInfo).data.userName ?: "",
            email = (state as RegistrationState.InputInfo).data.email ?: "",
            code = (state as RegistrationState.InputInfo).data.code ?: "",
            repeatCode = (state as RegistrationState.InputInfo).data.repeatCode ?: "",
            { viewModel.accept(InputInfo(it)) },
            { viewModel.accept(SignUpButtonClick(navController)) }
        )

        TextButton(
            onClick = {
                navController.popBackStack(Screen.AuthorizationScreen.route, false)
            },
            modifier = Modifier
                .navigationBarsPadding()
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