package com.example.superfit.presentation.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.superfit.domain.model.TrainingModel
import com.example.superfit.common.TrainingType
import com.example.superfit.domain.usecase.profile.body.parameters.GetBodyParametersHistoryUseCase
import com.example.superfit.domain.usecase.storage.credentials.DeleteUserEmailFromLocalStorageUseCase
import com.example.superfit.domain.usecase.storage.token.DeleteTokenFromLocalStorageUseCase
import com.example.superfit.domain.usecase.training.api.GetUserTrainingListUseCase
import com.example.superfit.navigation.Screen
import com.example.superfit.presentation.main.MainEvent.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val getBodyParametersHistoryUseCase: GetBodyParametersHistoryUseCase,
    private val getUserTrainingListUseCase: GetUserTrainingListUseCase,
    private val deleteTokenFromLocalStorageUseCase: DeleteTokenFromLocalStorageUseCase,
    private val deleteUserEmailFromLocalStorageUseCase: DeleteUserEmailFromLocalStorageUseCase
): ViewModel() {

    private val _state: MutableState<MainState> =
        mutableStateOf(MainState.Initial)
    var state: State<MainState> = _state

    private val _error: MutableState<List<Int>> =
        mutableStateOf(listOf())
    var error: State<List<Int>> = _error

    private fun hideError() {
        _error.value = emptyList()
    }

    fun initialize() {
        _state.value = MainState.Loading
        viewModelScope.launch {
            try {
                var myWeight: Int? = null
                var myHeight: Int? = null
                val bodyParamsList = getBodyParametersHistoryUseCase()
                if (bodyParamsList.isNotEmpty()) {
                    myWeight = bodyParamsList.last().weight
                    myHeight = bodyParamsList.last().height
                }

                val exercisesList = getUserTrainingListUseCase()
                val lastExercises = getLastExercises(exercisesList)

                _state.value = MainState.Content(
                    myWeight,
                    myHeight,
                    lastExercises
                )

            } catch (ex: Exception) {

            }
        }
    }

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
            lastExercises[0] = Pair(exercisesList.last().exercise, exercisesList.last().repeatCount )

            for(exercise in exercisesList) {
                if (exercise.exercise != exercisesList.last().exercise) {
                    lastExercises[1] = Pair(exercise.exercise, exercise.repeatCount)
                    break
                }
            }
        }

        return lastExercises
    }

    fun accept(event: MainEvent) {
        when(event) {
            is OnMyBodyClick          -> navigateToMyBodyScreen(event.navController)
            is OnSeeAllExercisesClick -> navigateToExercisesScreen(event.navController)
            is OnExerciseCardClick    -> navigateToExercise(event.navController, event.exercise)
            is OnSignOutClick         -> signOut(event.navController)
        }
    }

    private fun navigateToMyBodyScreen(navController: NavController) {

    }

    private fun navigateToExercisesScreen(navController: NavController) {
        navController.navigate(Screen.ExercisesScreen.route)
    }

    private fun navigateToExercise(
        navController: NavController,
        exercise: TrainingType
    ) {
        navController.navigate(
            Screen.ExerciseScreen.passExerciseInfo(exercise.name)
        )
    }

    private fun signOut(navController: NavController) {
        deleteTokenFromLocalStorageUseCase.execute()
        deleteUserEmailFromLocalStorageUseCase.execute()
        navController.navigate(Screen.AuthorizationScreen.route) {
            popUpTo(Screen.MainScreen.route) {
                inclusive = true
            }
        }
    }

}