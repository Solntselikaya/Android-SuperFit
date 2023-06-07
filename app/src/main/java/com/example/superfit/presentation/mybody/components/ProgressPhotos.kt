package com.example.superfit.presentation.mybody.components

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.LightGray
import com.example.superfit.presentation.ui.theme.Purple
import com.example.superfit.presentation.ui.theme.White

@Composable
fun ProgressPhotos(
    imageBefore: Bitmap? = null,
    dateBefore: String? = null,
    imageAfter: Bitmap? = null,
    dateAfter: String? = null,
    onAddButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(8.dp))
            .background(LightGray)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            PhotoWithDate(modifier = Modifier.weight(0.5f), image = imageBefore, date = dateBefore) {}
            Spacer(modifier = Modifier
                .width(4.dp)
                .background(White)
            )
            PhotoWithDate(modifier = Modifier.weight(0.5f), image = imageAfter, date = dateAfter) {}
        }

        FloatingActionButton(
            onClick = { onAddButtonClick() },
            modifier = Modifier
                .size(28.dp)
                .padding(end = 8.dp, bottom = 8.dp)
                .align(Alignment.BottomEnd),
            backgroundColor = White,
            contentColor = Purple,
            elevation = FloatingActionButtonDefaults.elevation(0.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_plus_photo),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp),
                tint = Purple
            )
        }
    }
}

@Preview
@Composable
fun ProgressPhotosPreview() {
    ProgressPhotos() {}
}