package com.example.superfit.presentation.registration

import androidx.navigation.NavController

sealed class RegistrationState {
    object Loading : RegistrationState()
    class InputInfo(
        val data: RegisterBody
    ) : RegistrationState()
}