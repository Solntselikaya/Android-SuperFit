package com.example.superfit.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.White

@Composable
fun TopImage(
    showBackButton: Boolean = true,
    onBackPressed: () -> Unit
) {
    Box(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.background_main),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(168.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.Crop
        )

        if (showBackButton) {
            IconButton(
                onClick = { onBackPressed() },
                modifier = Modifier
                    .padding(top = 38.dp, start = 16.dp)
                    .align(Alignment.TopStart),
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = null,
                    tint = White
                )
            }
        }

        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier
                .align(Alignment.Center),
            style = MaterialTheme.typography.h4,
            color = White
        )

        Box(
            Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(
                    White,
                    RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .height(24.dp)
        )
    }
}

@Preview
@Composable
fun TopImagePreview() {
    TopImage(showBackButton = true) {}
}