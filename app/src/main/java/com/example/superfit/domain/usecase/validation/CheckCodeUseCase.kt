package com.example.superfit.domain.usecase.validation

import com.example.superfit.R

class CheckCodeUseCase {
    operator fun invoke(code: String): Int {
        if (code.length != 4) {
            return R.string.code_length_error_message
        }

        return -1
    }
}