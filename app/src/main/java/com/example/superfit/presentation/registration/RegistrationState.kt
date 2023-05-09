package com.example.superfit.presentation.registration

sealed class RegistrationState {
    class InputInfo(
        val data: RegisterBody
    ) : RegistrationState()
}