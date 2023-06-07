package com.example.superfit.presentation.registration

import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.domain.model.toAccessTokenDto
import com.example.superfit.domain.usecase.auth.LoginUseCase
import com.example.superfit.domain.usecase.auth.RegisterUseCase
import com.example.superfit.domain.usecase.db.InitExerciseDatabaseForUserUseCase
import com.example.superfit.domain.usecase.storage.credentials.SaveUserEmailInLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.token.SaveTokenToLocalStorageUseCase
import com.example.superfit.domain.usecase.validation.CheckCodeRepeatUseCase
import com.example.superfit.domain.usecase.validation.CheckCodeUseCase
import com.example.superfit.domain.usecase.validation.CheckEmailUseCase
import com.example.superfit.domain.usecase.validation.CheckFieldsFilledUseCase
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.registration.RegistrationEvent.InputInfoProcess
import com.example.superfit.presentation.registration.RegistrationEvent.OnDialogDismiss
import com.example.superfit.presentation.registration.RegistrationEvent.SignInButtonClick
import com.example.superfit.presentation.registration.RegistrationEvent.SignUpButtonClick
import com.example.superfit.presentation.registration.RegistrationState.InputInfo
import com.example.superfit.presentation.registration.RegistrationState.Loading
import kotlinx.coroutines.launch
import com.example.superfit.presentation.registration.RegistrationEvent as RegEvent
import com.example.superfit.presentation.registration.RegistrationState as RegState

class RegistrationViewModel(
    private val checkFieldsFilledUseCase: CheckFieldsFilledUseCase,
    private val checkEmailUseCase: CheckEmailUseCase,
    private val checkCodeUseCase: CheckCodeUseCase,
    private val checkCodeRepeatUseCase: CheckCodeRepeatUseCase,
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    private val saveTokenToLocalStorageUseCase: SaveTokenToLocalStorageUseCase,
    private val initExerciseDatabaseForUserUseCase: InitExerciseDatabaseForUserUseCase,
    private val saveUserEmailInLocalStorageUseCase: SaveUserEmailInLocalStorageUseCase
) : ViewModel() {

    private val _state: MutableState<RegState> =
        mutableStateOf(InputInfo(RegisterBody("", "", "", "")))
    var state: State<RegState> = _state

    private val _error: MutableState<List<Int>> =
        mutableStateOf(listOf())
    var error: State<List<Int>> = _error

    private fun hideError() {
        _error.value = emptyList()
    }

    // callback навигации
    // через граф навигации
    // через стейт(?)
    fun accept(event: RegEvent) {
        when (event) {
            OnDialogDismiss -> {
                hideError()
            }

            is InputInfoProcess -> {
                changeInfo(event.info)
            }

            is SignInButtonClick -> {
                navigateToAuthorizationScreen(event.navController)
            }

            is SignUpButtonClick -> {
                register(event.navController)
            }
        }
    }

    private fun changeInfo(
        newInfo: RegisterBody
    ) {
        val newData = (_state.value as InputInfo).data

        when {
            newInfo.userName != null -> newData.userName = newInfo.userName
            newInfo.email != null -> newData.email = newInfo.email
            checkCode(newInfo.code) -> newData.code = newInfo.code
            checkCode(newInfo.repeatCode) -> newData.repeatCode = newInfo.repeatCode
        }
        _state.value = InputInfo(newData)
    }

    private fun checkCode(code: String?): Boolean {
        return code != null
                && TextUtils.isDigitsOnly(code)
                && code.length <= 4
                && !code.contains("0")
    }

    private fun navigateToAuthorizationScreen(navController: NavController) {
        navController.navigate(Screen.AuthorizationScreen.route)
    }

    private fun register(navController: NavController) {
        checkFields()
        if (_error.value.isNotEmpty()) {
            return
        }

        val currData = (_state.value as InputInfo).data
        _state.value = Loading
        viewModelScope.launch {
            try {
                registerUseCase(
                    currData.email ?: "",
                    currData.code ?: ""
                )
                val tokenModel = loginUseCase(
                    currData.email ?: "",
                    currData.code ?: ""
                )

                saveTokenToLocalStorageUseCase.execute(tokenModel.toAccessTokenDto())
                saveUserEmailInLocalStorageUseCase.execute(currData.email ?: "")
                initExerciseDatabaseForUserUseCase()

                navigateToMainScreen(navController)
            } catch (ex: Exception) {
                _state.value = InputInfo(currData)
                _error.value = when (ex.message.toString()) {
                    "HTTP 400" -> mutableListOf(R.string.email_already_used)
                    else -> mutableListOf(R.string.something_went_wrong)
                }
            }
        }
    }

    private fun checkFields() {
        val currData = (_state.value as InputInfo).data

        val check = checkFieldsFilledUseCase(
            currData.userName ?: "",
            currData.email ?: "",
            currData.code ?: "",
            currData.repeatCode ?: ""
        )
        if (check != -1) {
            _error.value = mutableListOf(check)
            return
        }

        val newError = mutableListOf<Int>()
        val emailCheck = checkEmailUseCase(currData.email ?: "")
        if (emailCheck != -1) {
            newError.add(emailCheck)
        }

        val codeCheck = checkCodeUseCase(currData.code ?: "")
        if (codeCheck != -1) {
            newError.add(codeCheck)
        }

        val codeRepCheck = checkCodeRepeatUseCase(
            currData.code ?: "",
            currData.repeatCode ?: ""
        )
        if (codeRepCheck != -1) {
            newError.add(codeRepCheck)
        }

        _error.value = newError
    }

    private fun navigateToMainScreen(navController: NavController) {
        navController.navigate(Screen.MainScreen.route) {

        }
    }

}