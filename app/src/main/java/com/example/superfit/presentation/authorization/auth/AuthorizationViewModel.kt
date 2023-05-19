package com.example.superfit.presentation.authorization.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.superfit.domain.usecase.storage.credentials.GetUserEmailFromLocalStorageUseCase
import com.example.superfit.domain.usecase.validation.CheckUserNameUseCase
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.authorization.auth.AuthorizationEvent.*
import com.example.superfit.presentation.authorization.auth.AuthorizationEvent as AuthEvent
import com.example.superfit.presentation.authorization.auth.AuthorizationState as AuthState

class AuthorizationViewModel(
    private val checkUserNameUseCase: CheckUserNameUseCase,
    private val getUserEmailFromLocalStorageUseCase: GetUserEmailFromLocalStorageUseCase
) : ViewModel() {

    private val _state: MutableState<AuthState> =
        mutableStateOf(AuthState.InputUserName(""))
    var state: State<AuthState> = _state

    init {
        val savedEmail = getUserEmailFromLocalStorageUseCase.execute()
        if (savedEmail.isNotBlank()) {
            _state.value = AuthState.InputUserName(savedEmail)
        }
    }

    private val _error: MutableState<List<Int>> =
        mutableStateOf(listOf())
    var error: State<List<Int>> = _error

    private fun hideError() {
        _error.value = emptyList()
    }

    fun accept(event: AuthEvent) {
        when(event) {
            OnDialogDismiss         -> { hideError() }
            is InputUserNameProcess -> { changeName(event.userName) }
            is SignInButtonClick    -> { navigateToPINScreen(event.navController, event.name) }
            is SignUpButtonClick    -> { navigateToRegistrationScreen(event.navController) }
        }
    }

    private fun changeName(newName: String) {
        _state.value = AuthState.InputUserName(newName)
    }

    private fun navigateToPINScreen(navController: NavController, name: String) {
        val result = checkUserNameUseCase(name)
        if (result != -1) {
            val message = mutableListOf(result)
            _error.value = message
        } else {
            navController.navigate(
                Screen.PINScreen.passUserNameInfo(name)
            )
        }
    }

    private fun navigateToRegistrationScreen(navController: NavController) {
        navController.navigate(Screen.RegistrationScreen.route)
    }

}