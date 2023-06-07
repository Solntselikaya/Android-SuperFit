package com.example.superfit.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Constraints
import androidx.navigation.NavController
import com.example.superfit.presentation.common.components.ErrorDialog
import com.example.superfit.presentation.common.components.LoadingBar
import com.example.superfit.presentation.common.components.TopImage
import com.example.superfit.presentation.common.extensions.constrainSize
import com.example.superfit.presentation.common.extensions.onMeasureConstraints
import com.example.superfit.presentation.main.MainEvent.InitScreen
import com.example.superfit.presentation.main.MainState.Content
import com.example.superfit.presentation.main.MainState.Loading
import com.example.superfit.presentation.main.components.MainScreenContent
import com.example.superfit.presentation.ui.theme.Black
import com.example.superfit.presentation.ui.theme.White
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = getViewModel()
) {
    val state: MainState by remember { viewModel.state }

    LaunchedEffect(Unit) {
        viewModel.accept(InitScreen(navController))
    }

    var constraints by remember { mutableStateOf(Constraints()) }
    Column(
        Modifier
            .fillMaxSize()
            .background(White)
            .onMeasureConstraints { constraints = it }
            .verticalScroll(rememberScrollState())
    ) {
        TopImage(false) {}

        when (state) {
            Loading -> LoadingBar(
                Modifier
                    .constrainSize { constraints }
                    .navigationBarsPadding(),
                Black
            )

            is Content -> {
                val err = (state as Content).error
                if (err != null) {
                    ErrorDialog(error = listOf(err)) {
                        
                    }
                }
                MainScreenContent(
                    navController,
                    viewModel,
                    (state as Content).myWeight,
                    (state as Content).myHeight,
                    (state as Content).lastExercises,
                    Modifier.weight(1f)
                )
            }
        }
    }
}