package com.example.superfit.presentation.imagelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.common.components.LoadingBar
import org.koin.androidx.compose.getViewModel
import com.example.superfit.presentation.imagelist.ImageListEvent.*
import com.example.superfit.presentation.imagelist.ImageListState.*
import com.example.superfit.presentation.imagelist.components.ImageListCell
import com.example.superfit.presentation.ui.theme.DarkGray
import com.example.superfit.presentation.ui.theme.White

@Composable
fun ImageListScreen(
    navController: NavController,
    viewModel: ImageListViewModel = getViewModel()
) {
    val state: ImageListState by remember { viewModel.state }

    LaunchedEffect(Unit) {
        viewModel.accept(InitScreen(navController = navController))
    }

    when (state) {
        Loading   -> {
            LoadingBar(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkGray),
                color = White
            )
        }

        is Content -> {
            val photos = (state as Content).images
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkGray)
            ) {
                ImageListCell(photos) { viewModel.accept(OnPhotoClick(it)) }

                IconButton(
                    onClick = { viewModel.accept(OnBackPressed) },
                    modifier = Modifier
                        .padding(top = 38.dp)
                        .align(Alignment.TopStart),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = null,
                        tint = White
                    )
                }
            }
        }

        is Error   -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkGray)
            ) {
                Text(
                    text = stringResource((state as Error).message),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.h3,
                    color = White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}