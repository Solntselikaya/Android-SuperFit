package com.example.superfit.presentation.exercise.pushups

import android.hardware.Sensor
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
import com.example.superfit.presentation.exercise.common.sensors.AccelerometerSensor
import com.example.superfit.presentation.exercise.pushups.PushUpsEvent.InitScreen
import com.example.superfit.presentation.exercise.pushups.PushUpsEvent.OnBackButtonClick
import com.example.superfit.presentation.exercise.pushups.PushUpsEvent.OnDismissErrorDialog
import com.example.superfit.presentation.exercise.pushups.PushUpsEvent.OnFinishClick
import com.example.superfit.presentation.exercise.pushups.PushUpsEvent.OnGoHomeClick
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

class PushUpsViewModel(
    private val accelerometerSensor: AccelerometerSensor,
    private val getExerciseRepeatCountForUserFromDatabaseUseCase: GetExerciseRepeatCountForUserFromDatabaseUseCase,
    private val increaseExerciseCountUseCase: IncreaseExerciseCountUseCase,
    private val saveUserTrainingUseCase: SaveUserTrainingUseCase,
    val exercise: TrainingType = TrainingType.PUSH_UP
) : ViewModel() {

    private val _state: MutableState<ExerciseState> =
        mutableStateOf(Initial())
    var state: State<ExerciseState> = _state

    private lateinit var navController: NavController

    fun accept(event: PushUpsEvent) {
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

    private var startedMovingDown: Boolean = false
    private var endedMovingDown: Boolean = false
    private var startedMovingUp: Boolean = false
    private var endedMovingUp: Boolean = false

    private fun clearMoves() {
        startedMovingDown = false
        endedMovingDown = false
        startedMovingUp = false
        endedMovingUp = false
    }

    private var prevZ: Int = -1

    private fun setAccelerometerSensor() {
        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { values ->
            if (prevZ == -1) {
                prevZ = values[2].toInt().absoluteValue - Sensor.TYPE_GRAVITY
            }
            val currZ = (values[2].absoluteValue - Sensor.TYPE_GRAVITY).toInt()
            val del = (prevZ.absoluteValue - currZ.absoluteValue).absoluteValue

            when {
                del >= 2 && !startedMovingDown && !startedMovingUp -> startedMovingDown = true
                del == 0 && startedMovingDown && !startedMovingUp  -> endedMovingDown = true
                del >= 2 && endedMovingDown && !startedMovingUp    -> startedMovingUp = true
                del == 0 && endedMovingDown && startedMovingUp     -> {
                    val currValues = (_state.value as DoExercise)

                    if (currValues.totalCount == currValues.currCount + 1) {
                        saveExercise(currValues.totalCount, currValues.currCount + 1)
                        _state.value = Success(exercise)
                    } else {
                        _state.value = DoExercise(
                            currValues.totalCount,
                            currValues.currCount + 1
                        )
                    }
                    clearMoves()
                }
            }
            prevZ = (values[2].absoluteValue - Sensor.TYPE_GRAVITY).toInt()
        }
    }

    private fun saveExercise(
        totalCount: Int,
        currCount: Int
    ) {
        accelerometerSensor.stopListening()

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