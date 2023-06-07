package com.example.superfit.presentation.registration

data class RegisterBody(
    var userName: String? = null,
    var email: String? = null,
    var code: String? = null,
    var repeatCode: String? = null
)
