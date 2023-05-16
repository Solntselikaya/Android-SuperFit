package com.example.superfit.domain.usecase.validation

import com.example.superfit.R

class CheckFieldsFilledUseCase {
    operator fun invoke(
        userName: String,
        email: String,
        code: String,
        codeRepeat: String
    ): Int {
        if (userName.isEmpty()
            || email.isEmpty()
            || code.isEmpty()
            || codeRepeat.isEmpty()
        ) {
            return R.string.fill_all_fields
        }

        return -1
    }
}