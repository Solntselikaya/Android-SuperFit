package com.example.superfit.presentation.exercise.crunch

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.common.TrainingType
import com.example.superfit.domain.usecase.training.api.SaveUserTrainingUseCase
import com.example.superfit.domain.usecase.training.db.GetExerciseRepeatCountForUserFromDatabaseUseCase
import com.example.superfit.domain.usecase.training.db.IncreaseExerciseCountUseCase
import com.example.superfit.presentation.exercise.common.ExerciseState
import com.example.superfit.presentation.exercise.common.ExerciseState.DoExercise
import com.example.superfit.presentation.exercise.common.ExerciseState.Initial
import com.example.superfit.presentation.exercise.common.ExerciseState.Loading
import com.example.superfit.presentation.exercise.crunch.CrunchEvent.InitScreen
import com.example.superfit.presentation.exercise.crunch.CrunchEvent.OnBackButtonClick
import com.example.superfit.presentation.exercise.crunch.CrunchEvent.OnDismissErrorDialog
import com.example.superfit.presentation.exercise.crunch.CrunchEvent.OnFinishClick
import kotlinx.coroutines.launch

class CrunchViewModel(
    private val getExerciseRepeatCountForUserFromDatabaseUseCase: GetExerciseRepeatCountForUserFromDatabaseUseCase,
    private val increaseExerciseCountUseCase: IncreaseExerciseCountUseCase,
    private val saveUserTrainingUseCase: SaveUserTrainingUseCase,
    val exercise: TrainingType = TrainingType.CRUNCH
) : ViewModel() {

    private val _state: MutableState<ExerciseState> =
        mutableStateOf(Initial())
    var state: State<ExerciseState> = _state

    private lateinit var navController: NavController

    fun accept(event: CrunchEvent) {
        when (event) {
            is InitScreen        -> getExercises(event.navController)
            is OnBackButtonClick -> navigateBack()
            is OnFinishClick     -> onFinishButtonClick(event.totalCount)
            OnDismissErrorDialog -> onDismissErrorDialog()
        }
    }

    private fun getExercises(nav: NavController) {
        _state.value = Loading

        viewModelScope.launch {
            val totalCount = getExerciseRepeatCountForUserFromDatabaseUseCase(exercise.name)
            _state.value = DoExercise(totalCount, 0)
        }
        navController = nav
    }

    private fun onFinishButtonClick(
        totalCount: Int
    ) {
        _state.value = Loading

        viewModelScope.launch {
            try {
                saveUserTrainingUseCase(exercise.name, totalCount)
                increaseExerciseCountUseCase(exercise.name)
                navigateBack()
            } catch (ex: Exception) {
                _state.value =
                    (_state.value as DoExercise).copy(error = R.string.something_went_wrong)
            }
        }

    }

    private fun navigateBack() {
        navController.popBackStack()
    }

    private fun onDismissErrorDialog() {
        _state.value = (_state.value as DoExercise).copy(error = null)
    }

}