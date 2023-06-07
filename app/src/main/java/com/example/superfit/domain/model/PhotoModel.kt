package com.example.superfit.domain.model

import okhttp3.ResponseBody

data class PhotoModel(
    val date: Long,
    val photo: ResponseBody
)
