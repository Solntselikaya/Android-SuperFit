package com.example.superfit.presentation.common.extensions

import android.content.Context
import android.net.Uri
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDateString(): String {
    val dateTime = Date(this * 1000L)
    val format = SimpleDateFormat("dd-MM-yyyy", Locale.US)
    return format.format(dateTime)
}

fun Long.toDateStringWithDots() : String {
    val dateTime = Date(this * 1000L)
    val format = SimpleDateFormat("dd.MM.yyyy", Locale.US)
    return format.format(dateTime)
}

fun Uri.toByteArray(context: Context): ByteArray {
    val inputStream = context.contentResolver.openInputStream(this)
    val buffer = ByteArrayOutputStream()
    val bufferSize = 1024
    val bufferArray = ByteArray(bufferSize)
    var len: Int
    while (inputStream?.read(bufferArray).also { len = it ?: 0 } != -1) {
        buffer.write(bufferArray, 0, len)
    }

    return buffer.toByteArray()
}