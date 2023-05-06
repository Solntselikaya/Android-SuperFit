package com.example.superfit.presentation.registration

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.superfit.domain.usecase.CheckCodeRepeatUseCase
import com.example.superfit.domain.usecase.CheckCodeUseCase
import com.example.superfit.domain.usecase.CheckEmailUseCase
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.registration.RegistrationEvent.*
import com.example.superfit.presentation.registration.RegistrationEvent as RegEvent
import com.example.superfit.presentation.registration.RegistrationState as RegState

class RegistrationViewModel : ViewModel() {

    private val _state: MutableState<RegState> =
        mutableStateOf(RegState.InputInfo(RegisterBody("", "", "", "")))
    var state: State<RegState> = _state

    private val _errorMessage: MutableState<List<Int>> =
        mutableStateOf(listOf())
    var errorMessage: State<List<Int>> = _errorMessage
    //var errorMessage: MutableList<Int> = mutableListOf()

    fun accept(event: RegEvent) {
        when(event) {
            OnDialogDismiss      -> onDialogDismiss()
            is InputInfo         -> changeInfo(event.info)
            is SignInButtonClick -> navigateToAuthorizationScreen(event.navController)
            is SignUpButtonClick -> register(event.navController)
        }
    }

    private fun onDialogDismiss() {
        _errorMessage.value = listOf()
    }

    private fun changeInfo(
        newInfo: RegisterBody
    ) {
        val newData = (_state.value as RegState.InputInfo).data

        when {
            newInfo.userName != null   -> newData.userName = newInfo.userName
            newInfo.email != null      -> newData.email = newInfo.email
            newInfo.code != null       -> newData.code = newInfo.code
            newInfo.repeatCode != null -> newData.repeatCode = newInfo.repeatCode
        }
        _state.value = RegState.InputInfo(newData)
    }

    private fun navigateToAuthorizationScreen(navController: NavController) {
        navController.navigate(Screen.AuthorizationScreen.route)
    }

    private fun register(navController: NavController) {
        val currData = (_state.value as RegState.InputInfo).data

        val checkEmailResult = CheckEmailUseCase().invoke(currData.email ?: "")
        val checkCodeResult = CheckCodeUseCase().invoke(currData.code ?: "")
        val checkCodeRepeatResult = CheckCodeRepeatUseCase().invoke(currData.code ?: "", currData.repeatCode ?: "")

        if (checkEmailResult != -1) {
            val currError = _errorMessage.value.toMutableList()
            _errorMessage.value = currError.plus(checkEmailResult)
        }
        if (checkCodeResult != 1) {
            val currError = _errorMessage.value.toMutableList()
            _errorMessage.value = currError.plus(checkCodeResult)
        }
        if (checkCodeRepeatResult != -1) {
            val currError = _errorMessage.value.toMutableList()
            _errorMessage.value = currError.plus(checkCodeRepeatResult)
        }
    }

}