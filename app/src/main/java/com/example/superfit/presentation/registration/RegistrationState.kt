package com.example.superfit.presentation.registration

// это усложнение
// если состояния не пересекаются, но их мало => единый дата-класс
// тогда кастить не надо будет
sealed class RegistrationState {
    object Loading : RegistrationState()
    class InputInfo(
        val data: RegisterBody
    ) : RegistrationState()
}