package com.example.superfit.presentation.authorization.pin

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.superfit.presentation.authorization.pin.PINEvent.*
import com.example.superfit.presentation.authorization.pin.PINState as PINState

class PINViewModel : ViewModel() {

    private val _state: MutableState<PINState> =
        mutableStateOf(PINState.InputPIN("", ""))
    var state: State<PINState> = _state

    private val _numbers: MutableState<List<Int>> =
        mutableStateOf((1..9).toList().shuffled())
    var numbers: State<List<Int>> = _numbers

    /*private val _showError: MutableState<Boolean> =
        mutableStateOf(false)
    var showError: State<Boolean> = _showError*/

    fun accept(event: PINEvent) {
        when(event) {
            is BackButtonClick -> navigateBack(event.navController)
            is InputPIN        -> updatePIN(event.navController, event.name, event.pin)
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
            _state.value = PINState.Loading
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
        // TODO: add navigation to main screen
    }

}