package com.example.superfit.common

import com.example.superfit.R

enum class TrainingType(
    val title: Int,
    val description: Int,
    val image: Int,
    val countText: Int,
    val defaultMinCount: Int = 10
) {
    CRUNCH(
        R.string.crunch,
        R.string.crunch_description,
        R.drawable.crunch_image,
        R.string.need_to_do
    ),
    SQUATS(
        R.string.squats,
        R.string.squats_description,
        R.drawable.squats_image,
        R.string.times_left
    ),
    PUSH_UP(
        R.string.push_ups,
        R.string.push_ups_description,
        R.drawable.push_ups_image,
        R.string.times_left
    ),
    PLANK(
        R.string.plank,
        R.string.plank_description,
        R.drawable.plank_image,
        R.string.seconds_left,
        20
    ),
    RUNNING(
        R.string.running,
        R.string.running_description,
        R.drawable.running_image,
        R.string.meters_passed,
        1000
    )
}