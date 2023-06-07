package com.example.superfit.presentation.mybody.components

import com.example.superfit.R

enum class BodyParameter(val value: Int, val alertText: Int) {
    WEIGHT(R.string.weight, R.string.change_your_weight),
    HEIGHT(R.string.height, R.string.change_your_height)
}