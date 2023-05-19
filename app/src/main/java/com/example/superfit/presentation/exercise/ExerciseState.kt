package com.example.superfit.presentation.exercise

sealed class ExerciseState {
    object Loading : ExerciseState()
    object Success : ExerciseState()
    object UnSuccess : ExerciseState()
    class DoExercise(val count: Int) : ExerciseState()
}
