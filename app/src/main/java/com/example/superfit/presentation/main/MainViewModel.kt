package com.example.superfit.presentation.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.common.TrainingType
import com.example.superfit.domain.model.TrainingModel
import com.example.superfit.domain.usecase.profile.body.parameters.GetBodyParametersHistoryUseCase
import com.example.superfit.domain.usecase.storage.credentials.DeleteUserEmailFromLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.token.DeleteTokenFromLocalStorageUseCase
import com.example.superfit.domain.usecase.training.api.GetUserTrainingListUseCase
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.main.MainEvent.InitScreen
import com.example.superfit.presentation.main.MainEvent.OnDismissErrorDialog
import com.example.superfit.presentation.main.MainEvent.OnExerciseCardClick
import com.example.superfit.presentation.main.MainEvent.OnMyBodyClick
import com.example.superfit.presentation.main.MainEvent.OnSeeAllExercisesClick
import com.example.superfit.presentation.main.MainEvent.OnSignOutClick
import com.example.superfit.presentation.main.MainState.Content
import com.example.superfit.presentation.main.MainState.Loading
import kotlinx.coroutines.launch

class MainViewModel(
    private val getBodyParametersHistoryUseCase: GetBodyParametersHistoryUseCase,
    private val getUserTrainingListUseCase: GetUserTrainingListUseCase,
    private val deleteTokenFromLocalStorageUseCase: DeleteTokenFromLocalStorageUseCase,
    private val deleteUserEmailFromLocalStorageUseCase: DeleteUserEmailFromLocalStorageUseCase
) : ViewModel() {

    private val _state: MutableState<MainState> =
        mutableStateOf(Loading)
    var state: State<MainState> = _state

    private lateinit var navController: NavController

    fun accept(event: MainEvent) {
        when (event) {
            is InitScreen          -> getData(event.navController)
            OnMyBodyClick          -> navigateToMyBodyScreen()
            OnSeeAllExercisesClick -> navigateToExercisesScreen()
            is OnExerciseCardClick -> navigateToExercise(event.exercise)
            OnSignOutClick         -> signOut()
            OnDismissErrorDialog   -> onDismissErrorDialog()
        }
    }

    private fun getData(nav: NavController) {
        navController = nav

        viewModelScope.launch {
            try {
                var myWeight: Int? = null
                var myHeight: Int? = null
                val bodyParamsList = getBodyParametersHistoryUseCase()
                if (bodyParamsList.isNotEmpty()) {
                    myWeight = bodyParamsList.first().weight
                    myHeight = bodyParamsList.first().height
                }

                val exercisesList = getUserTrainingListUseCase()
                val lastExercises = getLastExercises(exercisesList)

                _state.value = Content(
                    myWeight,
                    myHeight,
                    lastExercises
                )

            } catch (ex: Exception) {
                _state.value =
                    (_state.value as Content).copy(error = R.string.something_went_wrong)
            }
        }
    }

    // вынести в юз-кейс
    // ибо используется только тут
    private fun getLastExercises(
        exercisesList: List<TrainingModel>
    ): List<Pair<TrainingType, Int>> {
        val lastExercises: MutableList<Pair<TrainingType, Int>> = mutableListOf(
            Pair(TrainingType.PUSH_UP, TrainingType.PUSH_UP.defaultMinCount),
            Pair(TrainingType.PLANK, TrainingType.PLANK.defaultMinCount)
        )

        if (exercisesList.isNotEmpty() && exercisesList.size < 2) {
            when (exercisesList[0].exercise) {
                TrainingType.PUSH_UP -> {
                    lastExercises[0] = Pair(exercisesList[0].exercise, exercisesList[0].repeatCount)
                }

                else -> {
                    lastExercises[1] = Pair(exercisesList[0].exercise, exercisesList[0].repeatCount)
                }
            }

        } else if (exercisesList.isNotEmpty()) {
            lastExercises[0] = Pair(exercisesList.last().exercise, exercisesList.last().repeatCount)

            for (exercise in exercisesList) {
                if (exercise.exercise != exercisesList.last().exercise) {
                    lastExercises[1] = Pair(exercise.exercise, exercise.repeatCount)
                    break
                }
            }
        }

        return lastExercises
    }

    private fun navigateToMyBodyScreen() {
        navController.navigate(Screen.MyBodyScreen.route)
    }

    private fun navigateToExercisesScreen() {
        navController.navigate(Screen.ExercisesScreen.route)
    }

    private fun navigateToExercise(exercise: TrainingType) {
        val screen = when (exercise) {
            TrainingType.CRUNCH  -> Screen.CrunchScreen.route
            TrainingType.SQUATS  -> Screen.SquatsScreen.route
            TrainingType.PUSH_UP -> Screen.PushUpsScreen.route
            TrainingType.PLANK   -> Screen.PlankScreen.route
            TrainingType.RUNNING -> Screen.RunningScreen.route
        }
        navController.navigate(screen)
    }

    private fun signOut() {
        deleteTokenFromLocalStorageUseCase.execute()
        deleteUserEmailFromLocalStorageUseCase.execute()
        navController.navigate(Screen.AuthorizationScreen.route) {
            popUpTo(Screen.MainScreen.route) {
                inclusive = true
            }
        }
    }

    private fun onDismissErrorDialog() {
        _state.value = (_state.value as Content).copy(error = null)
    }

}