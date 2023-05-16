package com.example.superfit.domain.model

import com.example.superfit.R

enum class TrainingType(
    val title: Int,
    val description: Int,
    val image: Int
) {
    CRUNCH(R.string.crunch, R.string.crunch_description, R.drawable.crunch_image),
    SQUATS(R.string.squats, R.string.squats_description, R.drawable.squats_image),
    PUSH_UP(R.string.push_ups, R.string.push_ups_description, R.drawable.push_ups_image),
    PLANK(R.string.plank, R.string.plank_description, R.drawable.plank_image),
    RUNNING(R.string.running, R.string.running_description, R.drawable.running_image)
}