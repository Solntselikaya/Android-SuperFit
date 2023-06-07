package com.example.superfit.presentation.exercises

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.common.components.TopImage
import com.example.superfit.presentation.exercises.ExercisesEvent.InitScreen
import com.example.superfit.presentation.exercises.ExercisesEvent.OnBackButtonClick
import com.example.superfit.presentation.exercises.ExercisesEvent.OnExerciseCardClick
import com.example.superfit.presentation.exercises.components.ExercisesList
import com.example.superfit.presentation.ui.theme.Black
import com.example.superfit.presentation.ui.theme.White
import org.koin.androidx.compose.getViewModel

@Composable
fun ExercisesScreen(
    navController: NavController,
    viewModel: ExercisesViewModel = getViewModel()
) {
    viewModel.accept(InitScreen(navController))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        TopImage(true) { viewModel.accept(OnBackButtonClick) }

        Text(
            text = stringResource(R.string.exercises),
            modifier = Modifier.padding(start = 16.dp),
            color = Black,
            style = MaterialTheme.typography.h5
        )

        ExercisesList() { viewModel.accept(OnExerciseCardClick(it)) }
    }
}

/*
* val state = rememberCollapsingToolbarScaffoldState()
    CollapsingToolbarScaffold(
        modifier = Modifier.fillMaxSize(),
        state = state,
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        toolbar = {
            Image(
                painter = rememberAsyncImagePainter(movieViewModel.movieDetails!!.poster),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .parallax(0.5f)
                    .fillMaxWidth()
                    .height(250.dp)
                    .drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Transparent, Black),
                            startY = size.height / 2,
                            endY = size.height + 10
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient, blendMode = BlendMode.Darken)
                        }
                    }
                    .graphicsLayer {
                        // change alpha of Image as the toolbar expands
                        alpha = state.toolbarState.progress
                    }
            )
            * Row(
                Modifier
                    .pin()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    val onBackPressedDispatcher =
                        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
                    IconButton(
                        onClick = { onBackPressedDispatcher?.onBackPressed() },
                        Modifier
                            .padding(16.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.arrow_back),
                            contentDescription = null,
                            tint = White,
                        )
                    }
                }

                CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
                    IconButton(
                        onClick = { movieViewModel.onFavoritesChange(!isInFavorites) },
                        Modifier
                            .padding(16.dp),
                        enabled = state.toolbarState.progress.equals(0.toFloat())
                    ) {
                        Icon(
                            painter =
                            if (isInFavorites)
                                painterResource(R.drawable.filled_heart)
                            else
                                painterResource(R.drawable.unfilled_heart),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .graphicsLayer { alpha = 1 - state.toolbarState.progress },
                            tint = DarkRed
                        )
                    }
                }
            }

            val textSize = (24 + (12) * state.toolbarState.progress).sp
            val textLeftPadding = (49 - (33) * state.toolbarState.progress).dp
            val textRightPadding = (48 - (32) * state.toolbarState.progress).dp
            val textBottomPadding = (12 + (4) * state.toolbarState.progress).dp
            Text(
                text = movieViewModel.movieDetails!!.name,
                Modifier
                    .road(
                        whenCollapsed = TopStart,
                        whenExpanded = BottomStart
                    )
                    .padding(textLeftPadding, 9.dp, textRightPadding, textBottomPadding)
                    .defaultMinSize(minHeight = 32.dp),
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.h4,
                fontSize = textSize,
                color = White,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (state.toolbarState.progress.equals(0.toFloat())) 1 else Int.MAX_VALUE
            )
        }
    )
* */