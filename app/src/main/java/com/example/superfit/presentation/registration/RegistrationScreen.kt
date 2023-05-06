package com.example.superfit.presentation.registration

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.common.AppTitle
import com.example.superfit.presentation.common.InputField
import com.example.superfit.presentation.ui.theme.White
import com.example.superfit.presentation.registration.RegistrationEvent.*
import com.example.superfit.presentation.ui.theme.Black

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
        InputField(
            value = userName,
            placeHolderText = stringResource(R.string.user_name_input),
            onValueChanged = { onValueChanged(RegisterBody(userName = it)) }
        )

        InputField(
            value = email,
            placeHolderText = stringResource(R.string.email_input),
            keyBoardType = KeyboardType.Email,
            onValueChanged = { onValueChanged(RegisterBody(email = it)) }
        )

        InputField(
            value = code,
            placeHolderText = stringResource(R.string.code_input),
            keyBoardType = KeyboardType.Decimal,
            onValueChanged = { onValueChanged(RegisterBody(code = it)) }
        )

        InputField(
            value = repeatCode,
            placeHolderText = stringResource(R.string.repeat_code_input),
            keyBoardType = KeyboardType.Decimal,
            onValueChanged = { onValueChanged(RegisterBody(repeatCode = it)) }
        )

        TextButton(
            onClick = onSignUpButtonClick,
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