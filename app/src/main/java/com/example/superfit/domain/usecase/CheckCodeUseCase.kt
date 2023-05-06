package com.example.superfit.domain.usecase

import com.example.superfit.R

class CheckCodeUseCase {
    operator fun invoke(code: String): Int {
        if ("0" in code) {
            return R.string.code_error_message
        }
        if (code.length != 4) {
            return R.string.code_length_error_message
        }

        return -1
    }
}