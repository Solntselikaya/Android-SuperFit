package com.example.superfit.domain.usecase.validation

import android.util.Patterns
import com.example.superfit.R

class CheckEmailUseCase {
    operator fun invoke(email: String): Int {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return R.string.email_error_message
        }

        return -1
    }
}