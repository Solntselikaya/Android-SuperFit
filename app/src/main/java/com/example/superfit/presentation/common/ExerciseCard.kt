package com.example.superfit.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.DarkGray
import com.example.superfit.presentation.ui.theme.LightGray
import com.example.superfit.presentation.ui.theme.Transparent
import com.example.superfit.presentation.ui.theme.White

@Composable
fun ExerciseCard(
    image: Painter,
    name: String,
    description: String
) {
    Row(
        Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(DarkGray)
            .clickable {

            }
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .height(114.dp)
                .wrapContentWidth()
                .drawWithCache {
                    val gradient = Brush.horizontalGradient(
                        colors = listOf(Transparent, DarkGray),
                        startX = size.width - (size.width / 5),
                        endX = size.width
                    )
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Darken)
                    }
                },
            contentScale = ContentScale.FillHeight,
        )

        Column(
            Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = name,
                modifier = Modifier
                    .padding(bottom = 4.dp),
                color = White,
                style = MaterialTheme.typography.subtitle2
            )

            Text(
                text = description,
                color = LightGray,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseCardPreview() {
    ExerciseCard(
        image = painterResource(R.drawable.push_ups_image),
        name = stringResource(R.string.push_ups),
        description = stringResource(R.string.push_ups_description)
    )
}