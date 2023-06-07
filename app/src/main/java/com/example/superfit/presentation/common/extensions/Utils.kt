package com.example.superfit.presentation.common.extensions

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import com.example.superfit.BuildConfig
import com.example.superfit.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getOutputUri(context: Context): Uri {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val fileName = "IMG_$timeStamp.jpg"
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val imageFile = File(storageDir, fileName)
    val authority = BuildConfig.APPLICATION_ID + ".fileprovider"
    return FileProvider.getUriForFile(context, authority, imageFile)
}