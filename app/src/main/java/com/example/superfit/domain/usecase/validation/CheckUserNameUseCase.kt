package com.example.superfit.domain.usecase.validation

import com.example.superfit.R

class CheckUserNameUseCase {
    operator fun invoke(userName: String): Int {
        if (userName.isEmpty()) {
            return R.string.user_name_error_message
        }

        return -1
    }
}