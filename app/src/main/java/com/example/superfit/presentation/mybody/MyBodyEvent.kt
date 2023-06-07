package com.example.superfit.presentation.mybody

import androidx.navigation.NavController
import com.example.superfit.presentation.mybody.components.BodyParameter

sealed class MyBodyEvent {
    class InitScreen(val navController: NavController) : MyBodyEvent()

    class OnEditParameterClick(val type: BodyParameter) : MyBodyEvent()

    data class DoEditBodyParameter(val value: String? = null, val type: BodyParameter) : MyBodyEvent()

    object OnDismissChangeAlertDialog : MyBodyEvent()

    object OnAcceptChangeAlertDialog : MyBodyEvent()

    object OnDismissPickPhotoAlertDialog : MyBodyEvent()

    object OnSeeAllClick : MyBodyEvent()

    class OnPhotoClick(val image: ByteArray) : MyBodyEvent()

    object OnAddPhotoClick : MyBodyEvent()

    class OnSelectPhoto(val byteArray: ByteArray?) : MyBodyEvent()

    object OnTrainProgressClick : MyBodyEvent()

    object OnStatisticsClick : MyBodyEvent()

    object OnDismissErrorDialog : MyBodyEvent()
}
