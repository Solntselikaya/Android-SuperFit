package com.example.superfit.presentation.registration

sealed interface RegistrationState {
    class InputInfo(
        val data: RegisterBody
    ) : RegistrationState
}