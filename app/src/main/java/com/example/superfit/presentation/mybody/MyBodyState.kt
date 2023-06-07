package com.example.superfit.presentation.mybody

import android.graphics.Bitmap
import com.example.superfit.presentation.mybody.components.BodyParameter

sealed class MyBodyState {
    object Loading : MyBodyState()

    data class Content(
        val parameterValue: String? = null,
        val type: BodyParameter? = null,
        val weight: Int? = null,
        val height: Int? = null,
        val photoList: List<Pair<Bitmap?, String?>> = emptyList(),
        val showChangeStatAlertDialog: Boolean = false,
        val showPickPhotoAlertDialog: Boolean = false,
        val error: Int? = null
    ) : MyBodyState()
}
