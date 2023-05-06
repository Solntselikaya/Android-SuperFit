package com.example.superfit.presentation.registration

import androidx.navigation.NavController

interface RegistrationEvent {
    object OnDialogDismiss : RegistrationEvent
    class InputInfo(val info: RegisterBody): RegistrationEvent
    class SignInButtonClick(val navController: NavController) : RegistrationEvent
    class SignUpButtonClick(val navController: NavController) : RegistrationEvent
}