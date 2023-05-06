package com.example.superfit.domain.usecase

import com.example.superfit.R

class CheckCodeRepeatUseCase {
    operator fun invoke(code: String, codeRepeat: String): Int {
        if (code != codeRepeat) {
            return R.string.code_repeat_error_message
        }

        return -1
    }
}