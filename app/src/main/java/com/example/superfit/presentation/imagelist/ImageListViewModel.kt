package com.example.superfit.presentation.imagelist

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.superfit.presentation.imagelist.ImageListState.*
import com.example.superfit.presentation.imagelist.ImageListEvent.OnPhotoClick
import com.example.superfit.presentation.imagelist.ImageListEvent.OnBackPressed
import com.example.superfit.presentation.imagelist.ImageListEvent.*
import com.example.superfit.domain.usecase.profile.photo.GetAllPhotosUseCase
import com.example.superfit.presentation.common.PhotoData
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

class ImageListViewModel(
    val getAllPhotosUseCase: GetAllPhotosUseCase
): ViewModel() {
    private val _state: MutableState<ImageListState> =
        mutableStateOf(Loading)
    var state: State<ImageListState> = _state

    private lateinit var navController: NavController

    fun accept(event: ImageListEvent) {
        when (event) {
            is InitScreen        -> initScreen(event.navController)
            OnBackPressed        -> onBackPressed()
            is OnPhotoClick      -> onPhotoClick(event.image)
        }
    }

    private fun initScreen(nav: NavController) {
        navController = nav

        viewModelScope.launch {
            try {
                val photos = getAllPhotosUseCase()

                val rearranged = rearrangePhotosToGroups(photos)
                _state.value = Content(rearranged)
            } catch(ex: Exception) {
                Log.d("MY", ex.message.toString())
            }
        }
    }

    private fun onBackPressed() {
        navController.navigateUp()
    }

    private fun onPhotoClick(image: Bitmap) {

    }

    private fun rearrangePhotosToGroups(photos: List<PhotoData>): Map<String, List<PhotoData>> {
        val result: MutableMap<String, List<PhotoData>> = mutableMapOf()
        var list: MutableList<PhotoData> = mutableListOf()
        var date = ""
        for (i in photos.indices) {
            val currDate = getParsedDate(photos[i].date)
            if (date == "") {
                date = currDate
            }

            when {
                i == photos.lastIndex
                && date != currDate   -> {
                    result[date] = list.reversed()
                    list = mutableListOf()
                    list.add(photos[i])
                    result[currDate] = list.reversed()
                }

                i == photos.lastIndex -> {
                    list.add(photos[i])
                    result[date] = list.reversed()
                }

                date != currDate      -> {
                    result[date] = list.reversed()
                    list = mutableListOf()
                    list.add(photos[i])
                    date = currDate
                }
                else                  -> list.add(photos[i])
            }
        }

        return result
    }

    private fun getParsedDate(date: String): String {
        val splitedDate = date.split('.')
        val month = getMonthName(splitedDate[1].toInt() - 1)
        return "$month, ${splitedDate[2]}"
    }

    private fun getMonthName(monthNum: Int): String {
        val cal: Calendar = Calendar.getInstance()
        val monthDate = SimpleDateFormat("MMMM")
        cal.set(Calendar.MONTH, monthNum)

        return monthDate.format(cal.time)
    }
}