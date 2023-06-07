package com.example.superfit.presentation.imagelist.components

import android.graphics.Bitmap
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.superfit.presentation.common.PhotoData
import com.example.superfit.presentation.ui.theme.White

@Composable
fun ImageListCell(
    photos: Map<String, List<PhotoData>>? = emptyMap(),
    onPhotoClick: (Bitmap) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .scrollable(rememberScrollState(), Orientation.Vertical),
        contentPadding = PaddingValues(top = 80.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (photos != null) {
            val keys = photos.keys.toList()
            for (i in keys.indices) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text(
                        text = keys[i],
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(bottom = 12.dp, top = 16.dp),
                        color = White
                    )
                }

                val images = photos[keys[i]]
                if (images != null) {
                    items(images.size) { index ->
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(images[index].image)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier
                                .aspectRatio(1f)
                                .fillMaxSize()
                                .clickable { onPhotoClick(images[index].image) },
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}