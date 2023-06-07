package com.example.superfit.presentation.mybody

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.domain.usecase.profile.body.parameters.GetBodyParametersHistoryUseCase
import com.example.superfit.domain.usecase.profile.body.parameters.UpdateBodyParametersUseCase
import com.example.superfit.domain.usecase.profile.photo.GetProgressPhotosUseCase
import com.example.superfit.domain.usecase.profile.photo.UploadPhotoUseCase
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.mybody.MyBodyEvent.DoEditBodyParameter
import com.example.superfit.presentation.mybody.MyBodyEvent.InitScreen
import com.example.superfit.presentation.mybody.MyBodyEvent.OnAcceptChangeAlertDialog
import com.example.superfit.presentation.mybody.MyBodyEvent.OnAddPhotoClick
import com.example.superfit.presentation.mybody.MyBodyEvent.OnDismissChangeAlertDialog
import com.example.superfit.presentation.mybody.MyBodyEvent.OnDismissErrorDialog
import com.example.superfit.presentation.mybody.MyBodyEvent.OnDismissPickPhotoAlertDialog
import com.example.superfit.presentation.mybody.MyBodyEvent.OnEditParameterClick
import com.example.superfit.presentation.mybody.MyBodyEvent.OnPhotoClick
import com.example.superfit.presentation.mybody.MyBodyEvent.OnSeeAllClick
import com.example.superfit.presentation.mybody.MyBodyEvent.OnSelectPhoto
import com.example.superfit.presentation.mybody.MyBodyEvent.OnStatisticsClick
import com.example.superfit.presentation.mybody.MyBodyEvent.OnTrainProgressClick
import com.example.superfit.presentation.mybody.MyBodyState.Content
import com.example.superfit.presentation.mybody.MyBodyState.Loading
import com.example.superfit.presentation.mybody.components.BodyParameter
import kotlinx.coroutines.launch

class MyBodyViewModel(
    private val getBodyParametersHistoryUseCase: GetBodyParametersHistoryUseCase,
    private val updateBodyParametersUseCase: UpdateBodyParametersUseCase,
    private val getProgressPhotosUseCase: GetProgressPhotosUseCase,
    private val uploadPhotoUseCase: UploadPhotoUseCase
): ViewModel() {

    private val _state: MutableState<MyBodyState> =
        mutableStateOf(Loading)
    var state: State<MyBodyState> = _state

    private lateinit var navController: NavController

    fun accept(event: MyBodyEvent) {
        when (event) {
            is InitScreen                 -> initScreen(event.navController) //???
            is OnEditParameterClick       -> startEditParameter(event.type)
            is DoEditBodyParameter        -> changeBodyParameter(event.value, event.type)
            OnDismissChangeAlertDialog    -> dismissChangeAlertDialog()
            OnAcceptChangeAlertDialog     -> acceptChangeAlertDialog()
            OnDismissPickPhotoAlertDialog -> dismissPickPhotoAlertDialog()
            OnSeeAllClick                 -> navigateToAllPhotosScreen()
            is OnPhotoClick               -> onPhotoClick(event.image)
            OnAddPhotoClick               -> onAddPhotoClick()
            is OnSelectPhoto              -> addPhoto(event.byteArray)
            OnTrainProgressClick          -> navigateOnTrainProgressScreen()
            OnStatisticsClick             -> navigateOnStatisticsScreen()
            OnDismissErrorDialog          -> onDismissErrorDialog()
        }
    }

    private fun initScreen(nav: NavController) {
        navController = nav

        viewModelScope.launch {
            try {
                val paramsHistory = getBodyParametersHistoryUseCase()
                var weight: Int? = null
                var height: Int? = null
                if (paramsHistory.isNotEmpty()) {
                    weight = paramsHistory.first().weight
                    height = paramsHistory.first().height
                }

                val photos = getProgressPhotosUseCase()
                _state.value = Content(
                    weight = weight,
                    height = height,
                    photoList = photos
                )
            } catch(ex: Exception) {
                _state.value = Content(
                    error = R.string.something_went_wrong
                )
            }
        }
    }

    private fun startEditParameter(type: BodyParameter) {
        _state.value = (_state.value as Content).copy(type = type, showChangeStatAlertDialog = true)
    }

    private fun changeBodyParameter(newValue: String?, type: BodyParameter) {
        if (newValue != null && !newValue.all { it.isDigit() }) {
            return
        }

        if (type == BodyParameter.HEIGHT) {
            _state.value = (_state.value as Content).copy(parameterValue = newValue)
        } else {
            _state.value = (_state.value as Content).copy(parameterValue = newValue)
        }
    }

    private fun dismissChangeAlertDialog() {
        _state.value = (_state.value as Content).copy(showChangeStatAlertDialog = false)
    }

    private fun acceptChangeAlertDialog() {
        val currState = _state.value as Content

        if ((_state.value as Content).parameterValue == ""
            || (_state.value as Content).parameterValue == null
        ) {
            dismissChangeAlertDialog()
            return
        }

        _state.value = Loading
        viewModelScope.launch {
            try {
                if (currState.type == BodyParameter.WEIGHT) {
                    updateBodyParametersUseCase(
                        currState.height,
                        currState.parameterValue?.toInt()
                    )

                    _state.value = currState.copy(
                        parameterValue = "",
                        weight = currState.parameterValue?.toInt(),
                        showChangeStatAlertDialog = false
                    )
                } else {
                    updateBodyParametersUseCase(
                        currState.parameterValue?.toInt(),
                        currState.weight
                    )

                    _state.value = currState.copy(
                        parameterValue = "",
                        height = currState.parameterValue?.toInt(),
                        showChangeStatAlertDialog = false
                    )
                }
            } catch(ex: Exception) {
                _state.value = currState.copy(error = R.string.something_went_wrong)
            }
        }
    }

    private fun dismissPickPhotoAlertDialog(){
        _state.value = (_state.value as Content).copy(showPickPhotoAlertDialog = false)
    }

    private fun navigateToAllPhotosScreen() {
        navController.navigate(Screen.ImageListScreen.route)
    }

    private fun onPhotoClick(image: ByteArray) {

    }

    private fun onAddPhotoClick() {
        _state.value = (_state.value as Content).copy(showPickPhotoAlertDialog = true)
    }

    private fun addPhoto(byteArray: ByteArray?) {
        if (byteArray == null)
            return

        val currState = _state.value as Content
        _state.value = Loading
        viewModelScope.launch {
            try {
                uploadPhotoUseCase(byteArray)
                initScreen(navController)
            } catch(ex: Exception) {
                _state.value = currState.copy(
                    showChangeStatAlertDialog = false,
                    showPickPhotoAlertDialog = false,
                    error = R.string.something_went_wrong
                )
            }
        }
    }

    private fun navigateOnTrainProgressScreen() {

    }

    private fun navigateOnStatisticsScreen() {

    }

    private fun onDismissErrorDialog() {
        _state.value = (_state.value as Content).copy(error = null)
    }
}