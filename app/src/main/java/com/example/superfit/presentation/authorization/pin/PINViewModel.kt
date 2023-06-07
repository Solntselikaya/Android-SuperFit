package com.example.superfit.presentation.authorization.pin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.domain.model.toAccessTokenDto
import com.example.superfit.domain.usecase.auth.LoginUseCase
import com.example.superfit.domain.usecase.storage.credentials.SaveUserEmailInLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.token.SaveTokenToLocalStorageUseCase
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.authorization.pin.PINEvent.BackButtonClick
import com.example.superfit.presentation.authorization.pin.PINEvent.InputPINProcess
import com.example.superfit.presentation.authorization.pin.PINEvent.OnDialogDismiss
import kotlinx.coroutines.launch

class PINViewModel(
    private val loginUseCase: LoginUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val saveUserEmailInLocalStorageUseCase: SaveUserEmailInLocalStorageUseCase
) : ViewModel() {

    private val _state: MutableState<PINState> =
        mutableStateOf(PINState.InputPIN("", ""))
    var state: State<PINState> = _state

    private val _numbers: MutableState<List<Int>> =
        mutableStateOf((1..9).toList().shuffled())
    var numbers: State<List<Int>> = _numbers

    private val _error: MutableState<List<Int>> =
        mutableStateOf(listOf())
    var error: State<List<Int>> = _error

    private fun hideError() {
        _error.value = emptyList()
    }

    fun accept(event: PINEvent) {
        when (event) {
            OnDialogDismiss    -> hideError()
            is BackButtonClick -> navigateBack(event.navController)
            is InputPINProcess -> updatePIN(event.navController, event.name, event.pin)
        }
    }

    private fun navigateBack(navController: NavController) {
        navController.popBackStack()
    }

    private fun updatePIN(
        navController: NavController,
        name: String,
        num: String
    ) {
        val newPin = (_state.value as PINState.InputPIN).pin + num

        if (newPin.length == 4) {
            login(navController, name, newPin)
        } else {
            _state.value = PINState.InputPIN(name, newPin)
            _numbers.value = _numbers.value.shuffled()
        }
    }

    private fun login(
        navController: NavController,
        name: String,
        pin: String
    ) {
        val currName = (_state.value as PINState.InputPIN).name
        _state.value = PINState.Loading

        viewModelScope.launch {
            try {
                val tokenModel = loginUseCase(name, pin)

                saveTokenToLocalStorageUseCase.execute(tokenModel.toAccessTokenDto())
                saveUserEmailInLocalStorageUseCase.execute(name)

                navigateToMainScreen(navController)
            } catch (ex: Exception) {
                _state.value = PINState.InputPIN(currName, "")

                _error.value = when (ex.message.toString()) {
                    "HTTP 404 " -> mutableListOf(R.string.invalid_credentials)
                    else        -> mutableListOf(R.string.something_went_wrong)
                }
            }
        }
    }

    private fun navigateToMainScreen(navController: NavController) {
        navController.navigate(Screen.MainScreen.route) {
            popUpTo(Screen.AuthorizationScreen.route) {
                saveState = false
                inclusive = true
            }
            restoreState = false
            launchSingleTop = true
        }
    }

}