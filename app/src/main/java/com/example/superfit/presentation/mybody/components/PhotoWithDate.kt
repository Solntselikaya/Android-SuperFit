package com.example.superfit.presentation.mybody.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.Purple
import com.example.superfit.presentation.ui.theme.White

@Composable
fun PhotoWithDate(
    modifier: Modifier = Modifier,
    image: Bitmap? = null,
    date: String? = null,
    onPhotoClick: (Bitmap?) -> Unit
) {
    Box(
        modifier = modifier
            .height(216.dp)
            .clickable { onPhotoClick(image) }
    ) {
        if (image == null) {
            Image(
                painter = painterResource(R.drawable.icon_image),
                contentDescription = null,
                modifier = Modifier
                    .height(24.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        } else {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .height(216.dp)
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop
            )
        }

        if (date != null) {
            Box(modifier = Modifier
                .padding(start = 8.dp, bottom = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Purple)
                .align(Alignment.BottomStart)
            ) {
                Text(
                    text = date,
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.caption,
                    color = White
                )
            }
        }
    }
}

@Preview
@Composable
fun PhotoWithDatePreview() {
    //PhotoWithDate(image = R.drawable.push_ups_image, date = "21.04.2019")
}