package com.example.superfit.presentation.exercise

import androidx.navigation.NavController
import com.example.superfit.common.TrainingType

sealed class ExerciseEvent {
    class DoExerciseProcess(val count: Int) : ExerciseEvent()
    class OnBackButtonClick(
        val navController: NavController
    ) : ExerciseEvent()
    class OnFinishClick(
        val navController: NavController,
        val exercise: TrainingType,
        val count: Int
    ) : ExerciseEvent()
    class OnGoHomeClick(val navController: NavController) : ExerciseEvent()
}
