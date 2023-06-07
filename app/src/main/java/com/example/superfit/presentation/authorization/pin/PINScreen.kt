package com.example.superfit.presentation.authorization.pin

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.common.Constants.USER_NAME
import com.example.superfit.presentation.authorization.pin.PINEvent.InputPINProcess
import com.example.superfit.presentation.authorization.pin.PINEvent.OnDialogDismiss
import com.example.superfit.presentation.authorization.pin.PINState.InputPIN
import com.example.superfit.presentation.authorization.pin.PINState.Loading
import com.example.superfit.presentation.authorization.pin.components.PINButtons
import com.example.superfit.presentation.common.components.AppTitle
import com.example.superfit.presentation.common.components.ErrorDialog
import com.example.superfit.presentation.common.components.LoadingBar
import com.example.superfit.presentation.ui.theme.White
import org.koin.androidx.compose.getViewModel

@Composable
fun PINScreen(
    userName: Bundle,
    navController: NavController
) {
    val viewModel = getViewModel<PINViewModel>()

    val name = userName.getString(USER_NAME).toString()
    val state: PINState by remember { viewModel.state }
    val numbers: List<Int> by remember { viewModel.numbers } //в стейт!
    val error: List<Int> by remember { viewModel.error }

    ErrorDialog(error = error) { viewModel.accept(OnDialogDismiss) }

    when (state) {
        is InputPIN -> PINScreenContent(navController, name, numbers, viewModel)
        is Loading -> LoadingBar(Modifier.fillMaxSize())
    }

}

@Composable
fun PINScreenContent(
    navController: NavController,
    name: String,
    numbers: List<Int>,
    viewModel: PINViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Icon(
            painter = painterResource(R.drawable.arrow_back),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 40.dp)
                .clickable {
                    navController.popBackStack()
                },
            tint = White
        )

        AppTitle(Modifier.align(Alignment.TopCenter))

        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                modifier = Modifier
                    .padding(bottom = 40.dp),
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center
            )

            PINButtons(
                numbers
            ) { viewModel.accept(InputPINProcess(navController, name, it)) }
        }
    }
}