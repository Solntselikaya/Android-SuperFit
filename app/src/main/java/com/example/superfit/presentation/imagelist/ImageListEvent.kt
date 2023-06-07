package com.example.superfit.presentation.imagelist

import android.graphics.Bitmap
import androidx.navigation.NavController

sealed class ImageListEvent {
    class InitScreen(val navController: NavController) : ImageListEvent()

    object OnBackPressed : ImageListEvent()

    class OnPhotoClick(val image: Bitmap) : ImageListEvent()
}
