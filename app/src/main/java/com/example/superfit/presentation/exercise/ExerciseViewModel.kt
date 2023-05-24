package com.example.superfit.presentation.exercise

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.superfit.common.TrainingType
import com.example.superfit.presentation.exercise.ExerciseEvent.*
import com.example.superfit.domain.usecase.db.GetExerciseRepeatCountFromDatabaseUseCase
import com.example.superfit.domain.usecase.training.api.SaveUserTrainingUseCase
import com.example.superfit.domain.usecase.training.db.GetExerciseRepeatCountForUserFromDatabaseUseCase
import com.example.superfit.domain.usecase.training.db.IncreaseExerciseCountUseCase
import com.example.superfit.presentation.exercise.ExerciseState.*
import kotlinx.coroutines.launch

class ExerciseViewModel(
    private val getExerciseRepeatCountForUserFromDatabaseUseCase: GetExerciseRepeatCountForUserFromDatabaseUseCase,
    private val increaseExerciseCountUseCase: IncreaseExerciseCountUseCase,
    private val saveUserTrainingUseCase: SaveUserTrainingUseCase
): ViewModel() {

    private val _state: MutableState<ExerciseState> =
        mutableStateOf(Initial)
    var state: State<ExerciseState> = _state

    private var currCount: Int = 10

    fun getCurrCount(exercise: TrainingType) {
        viewModelScope.launch {
            currCount = getExerciseRepeatCountForUserFromDatabaseUseCase(exercise.name)
            _state.value = DoExercise(currCount)
        }
    }

    fun accept(event: ExerciseEvent) {
        when (event) {
            is DoExerciseProcess -> {  }
            is OnBackButtonClick -> { navigateBack(event.navController) }
            is OnFinishClick     -> { onFinishButtonClick(event.navController, event.count, event.exercise) }
            is OnGoHomeClick     -> {  }
        }
    }

    private fun navigateBack(navController: NavController) {
        navController.popBackStack()
    }

    private fun onFinishButtonClick(
        navController: NavController,
        count: Int,
        exercise: TrainingType
    ) {
        when(exercise) {
            TrainingType.CRUNCH  -> { saveExercise(count, TrainingType.CRUNCH) }
            TrainingType.SQUATS  -> {  }
            TrainingType.PUSH_UP -> {  }
            TrainingType.PLANK   -> { savePlank(count) }
            TrainingType.RUNNING -> {  }
        }
        navController.popBackStack()
    }

    private fun saveExercise(count: Int, exercise: TrainingType) {
        viewModelScope.launch {
            increaseExerciseCountUseCase(exercise.name)
            saveUserTrainingUseCase(exercise.name, count)
        }
    }

    private fun savePlank(count: Int, exercise: TrainingType = TrainingType.PLANK) {
        if (count != 0) {
            _state.value = UnSuccess
        } else {
            _state.value = Success
        }
    }

}