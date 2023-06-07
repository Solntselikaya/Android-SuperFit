package com.example.superfit.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import okhttp3.ResponseBody

fun getImageBitmap(responseBody: ResponseBody): Bitmap {
    val byteArray = responseBody.bytes()
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}