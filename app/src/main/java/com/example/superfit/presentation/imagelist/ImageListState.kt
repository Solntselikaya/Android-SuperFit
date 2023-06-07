package com.example.superfit.presentation.imagelist

import com.example.superfit.presentation.common.PhotoData

sealed class ImageListState {
    object Loading : ImageListState()

    data class Content(
        val images: Map<String, List<PhotoData>> = emptyMap()
    ) : ImageListState()

    class Error(val message: Int) : ImageListState()
}
