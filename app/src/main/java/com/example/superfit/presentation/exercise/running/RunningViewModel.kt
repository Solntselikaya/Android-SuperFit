package com.example.superfit.presentation.exercise.running

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
import com.example.superfit.presentation.exercise.common.ExerciseState.Success
import com.example.superfit.presentation.exercise.common.ExerciseState.UnSuccess
import com.example.superfit.presentation.exercise.running.RunningEvent.InitScreen
import com.example.superfit.presentation.exercise.running.RunningEvent.OnBackButtonClick
import com.example.superfit.presentation.exercise.running.RunningEvent.OnDismissErrorDialog
import com.example.superfit.presentation.exercise.running.RunningEvent.OnFinishClick
import com.example.superfit.presentation.exercise.running.RunningEvent.OnGoHomeClick
import com.example.superfit.presentation.exercise.running.sensors.StepSensor
import kotlinx.coroutines.launch

class RunningViewModel(
    private val stepSensor: StepSensor,
    private val getExerciseRepeatCountForUserFromDatabaseUseCase: GetExerciseRepeatCountForUserFromDatabaseUseCase,
    private val increaseExerciseCountUseCase: IncreaseExerciseCountUseCase,
    private val saveUserTrainingUseCase: SaveUserTrainingUseCase,
    val exercise: TrainingType = TrainingType.RUNNING
) : ViewModel() {

    // в домен, т.к. логика приложения
    companion object {
        private const val defaultStepLength: Double = 0.55
    }

    private val _state: MutableState<ExerciseState> =
        mutableStateOf(Initial())
    var state: State<ExerciseState> = _state

    private lateinit var navController: NavController

    fun accept(event: RunningEvent) {
        when (event) {
            is InitScreen        -> initScreen(event.navController)
            is OnBackButtonClick -> navigateBack()
            is OnFinishClick     -> saveExercise(event.totalCount, event.currCount)
            is OnGoHomeClick     -> navigateBack()
            OnDismissErrorDialog -> onDismissErrorDialog()
        }
    }

    private fun initScreen(nav: NavController) {
        navController = nav

        viewModelScope.launch {
            val totalCount = getExerciseRepeatCountForUserFromDatabaseUseCase(exercise.name)
            _state.value = DoExercise(totalCount, 0)

            setAccelerometerSensor()
        }
    }

    private var initStepCount: Int = -1
    private fun setAccelerometerSensor() {
        stepSensor.startListening()
        stepSensor.setOnSensorValuesChangedListener { values ->
            val currCount = (values[0] * defaultStepLength).toInt()
            if (initStepCount == -1) {
                initStepCount = currCount
            }

            val currValues = (_state.value as DoExercise)
            val currSteps = currCount - initStepCount

            if (currValues.totalCount <= currSteps) {
                saveExercise(currValues.totalCount, currSteps)
            } else {
                _state.value = DoExercise(
                    currValues.totalCount,
                    currSteps
                )
            }
        }
    }

    private fun saveExercise(
        totalCount: Int,
        currCount: Int
    ) {
        stepSensor.stopListening()

        viewModelScope.launch {
            try {
                if (currCount != 0) {
                    saveUserTrainingUseCase(exercise.name, currCount)
                }

                if (totalCount != currCount) {
                    _state.value = UnSuccess(exercise, totalCount - currCount)
                } else {
                    _state.value = Success(exercise)
                    increaseExerciseCountUseCase(exercise.name)
                }
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