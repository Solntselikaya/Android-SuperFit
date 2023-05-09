package com.example.superfit.presentation.authorization.pin.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.superfit.presentation.ui.theme.White

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
        items(count = numbers.size, key = { numbers[it] }) {
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