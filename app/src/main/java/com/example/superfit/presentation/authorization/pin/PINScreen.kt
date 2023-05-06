package com.example.superfit.presentation.authorization.pin

import android.os.Bundle
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.navigation.USER_NAME
import com.example.superfit.presentation.common.AppTitle
import com.example.superfit.presentation.ui.theme.White

@Composable
fun PINScreen(
    userName: Bundle,
    navController: NavController,
    viewModel: PINViewModel = viewModel()
) {

    val name = userName.getString(USER_NAME).toString()
    val numbers: List<Int> by remember { viewModel.numbers }
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
            ) { viewModel.accept(PINEvent.InputPIN(navController, name, it)) }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PINButtons(
    numbers: List<Int>,
    onNumClick: (String) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .padding(horizontal = 38.dp)
            .padding(bottom = 108.dp),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(numbers.size) {
            TextButton(
                modifier = Modifier
                    .wrapContentSize()
                    .animateItemPlacement(
                        animationSpec = tween(
                            durationMillis = 1000
                        )
                    ),
                onClick = { onNumClick("${numbers[it]}") },
            ) {
                Text(
                    text = "${numbers[it]}",
                    style = MaterialTheme.typography.h3,
                    color = White,
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1f)
                        .aspectRatio(1f, true)
                        .border(2.dp, White, RoundedCornerShape(10.dp)),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}