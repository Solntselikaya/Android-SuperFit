package com.example.superfit.presentation.authorization.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.authorization.auth.AuthorizationEvent.*
import com.example.superfit.presentation.authorization.auth.AuthorizationEvent as AuthEvent
import com.example.superfit.presentation.authorization.auth.AuthorizationState as AuthState

class AuthorizationViewModel : ViewModel() {

    private val _state: MutableState<AuthState> =
        mutableStateOf(AuthState.InputUserName(""))
    var state: State<AuthState> = _state

    fun accept(event: AuthEvent) {
        when(event) {
            is InputUserName -> { changeName(event.userName) }
            is SignInButtonClick -> { navigateToPINScreen(event.navController, event.name) }
            is SignUpButtonClick -> { navigateToRegistrationScreen(event.navController) }
        }
    }

    private fun changeName(newName: String) {
        _state.value = AuthState.InputUserName(newName)
    }

    private fun navigateToPINScreen(navController: NavController, name: String) {
        navController.navigate(
            Screen.PINScreen.passUserNameInfo(name)
        )
    }

    private fun navigateToRegistrationScreen(navController: NavController) {
        navController.navigate(Screen.RegistrationScreen.route)
    }

}