package com.example.superfit.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.DarkGray
import com.example.superfit.presentation.ui.theme.LightGray
import com.example.superfit.presentation.ui.theme.Transparent

@Composable
fun MyBodyCard(
    weight: Int? = null,
    height: Int? = null,
    onClick: () -> Unit
) {

    val myWeight = if (weight != null) {
        stringResource(R.string.my_weight, weight)
    } else {
        stringResource(R.string.undefined)
    }

    val myHeight = if (height != null) {
        stringResource(R.string.my_height, height)
    } else {
        stringResource(R.string.undefined)
    }

    Row(
        Modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(RoundedCornerShape(8.dp))
            .background(DarkGray)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(R.drawable.my_body_image),
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .height(114.dp)
                .fillMaxWidth()
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
            contentScale = ContentScale.Crop,
        )

        Column(
            Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
        ) {
            MyBodyParameter(painterResource(R.drawable.icon_weight), myWeight)
            Spacer(modifier = Modifier.height(8.dp))
            MyBodyParameter(painterResource(R.drawable.icon_height), myHeight)

            Spacer(modifier = Modifier.weight(1.0f))

            Row {
                Text(
                    text = stringResource(R.string.details),
                    modifier = Modifier.padding(start = 4.dp),
                    color = LightGray,
                    style = MaterialTheme.typography.caption
                )

                Icon(
                    painter = painterResource(R.drawable.icon_long_arrow_right),
                    contentDescription = null,
                    tint = LightGray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyBodyCardPreview() {
    MyBodyCard() {}
}