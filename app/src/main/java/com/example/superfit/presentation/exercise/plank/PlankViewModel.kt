package com.example.superfit.presentation.exercise.plank

import android.os.CountDownTimer
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
import com.example.superfit.presentation.exercise.common.ExerciseState.Success
import com.example.superfit.presentation.exercise.common.ExerciseState.UnSuccess
import com.example.superfit.presentation.exercise.plank.PlankEvent.InitScreen
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnAcceptStartAlertDialog
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnBackButtonClick
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnDismissErrorDialog
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnDismissStartAlertDialog
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnFinishClick
import com.example.superfit.presentation.exercise.plank.PlankEvent.OnGoHomeClick
import kotlinx.coroutines.launch

class PlankViewModel(
    private val getExerciseRepeatCountForUserFromDatabaseUseCase: GetExerciseRepeatCountForUserFromDatabaseUseCase,
    private val increaseExerciseCountUseCase: IncreaseExerciseCountUseCase,
    private val saveUserTrainingUseCase: SaveUserTrainingUseCase,
    val exercise: TrainingType = TrainingType.PLANK
) : ViewModel() {

    private val _state: MutableState<ExerciseState> =
        mutableStateOf(Loading)
    var state: State<ExerciseState> = _state

    private lateinit var navController: NavController
    private lateinit var timer: CountDownTimer // в дату!

    fun accept(event: PlankEvent) {
        when (event) {
            is InitScreen               -> initScreen(event.navController)
            is OnAcceptStartAlertDialog -> acceptAlertDialog(event.totalCount)
            is OnFinishClick            -> onFinishButtonClick(event.totalCount, event.currCount)
            OnDismissErrorDialog        -> onDismissErrorDialog()
            OnDismissStartAlertDialog   -> navigateBack()
            OnBackButtonClick           -> navigateBack()
            OnGoHomeClick               -> navigateBack()
        }
    }

    private fun initScreen(nav: NavController) {
        navController = nav
        getExercises()
    }

    private fun acceptAlertDialog(totalCount: Int) {
        _state.value = DoExercise(totalCount, 0)
        timer.start()
    }

    private fun onFinishButtonClick(
        totalCount: Int,
        currCount: Int
    ) {
        timer.cancel()

        viewModelScope.launch {
            try {
                if (currCount != 0) {
                    saveUserTrainingUseCase(exercise.name, currCount)
                    increaseExerciseCountUseCase(exercise.name)
                }

                _state.value = if (totalCount != currCount) {
                    UnSuccess(exercise, totalCount - currCount)
                } else {
                    Success(exercise)
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

    private fun getExercises() {
        viewModelScope.launch {
            val totalCount = getExerciseRepeatCountForUserFromDatabaseUseCase(exercise.name)
            setupTimer(totalCount)

            _state.value = Initial(totalCount)
        }
    }

    private fun setupTimer(totalCount: Int) {
        timer = object : CountDownTimer(totalCount * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val currCount = (millisUntilFinished / 1000).toInt()
                _state.value = DoExercise(totalCount, totalCount - currCount)
            }

            override fun onFinish() {
                onFinishButtonClick(totalCount, totalCount)
            }
        }
    }

    private fun onDismissErrorDialog() {
        _state.value = (_state.value as DoExercise).copy(error = null)
    }

}