package com.example.superfit.presentation.mybody

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.superfit.R
import com.example.superfit.presentation.common.components.ErrorDialog
import com.example.superfit.presentation.common.components.LoadingBar
import com.example.superfit.presentation.mybody.MyBodyEvent.DoEditBodyParameter
import com.example.superfit.presentation.mybody.MyBodyEvent.InitScreen
import com.example.superfit.presentation.mybody.MyBodyEvent.OnAcceptChangeAlertDialog
import com.example.superfit.presentation.mybody.MyBodyEvent.OnAddPhotoClick
import com.example.superfit.presentation.mybody.MyBodyEvent.OnDismissChangeAlertDialog
import com.example.superfit.presentation.mybody.MyBodyEvent.OnEditParameterClick
import com.example.superfit.presentation.mybody.MyBodyEvent.OnSeeAllClick
import com.example.superfit.presentation.mybody.MyBodyEvent.OnStatisticsClick
import com.example.superfit.presentation.mybody.MyBodyEvent.OnTrainProgressClick
import com.example.superfit.presentation.mybody.MyBodyEvent.OnSelectPhoto
import com.example.superfit.presentation.mybody.MyBodyEvent.OnDismissPickPhotoAlertDialog
import com.example.superfit.presentation.mybody.MyBodyEvent.OnDismissErrorDialog
import com.example.superfit.presentation.mybody.MyBodyState.Content
import com.example.superfit.presentation.mybody.MyBodyState.Loading
import com.example.superfit.presentation.mybody.components.BodyParameter
import com.example.superfit.presentation.mybody.components.BodyStat
import com.example.superfit.presentation.mybody.components.ButtonWithArrowRight
import com.example.superfit.presentation.mybody.components.ChangeStatAlertDialog
import com.example.superfit.presentation.mybody.components.PickPhotoAlertDialog
import com.example.superfit.presentation.mybody.components.ProgressPhotos
import com.example.superfit.presentation.ui.theme.DarkGray
import com.example.superfit.presentation.ui.theme.LightGray
import com.example.superfit.presentation.ui.theme.White
import org.koin.androidx.compose.getViewModel

@Composable
fun MyBodyScreen(
    navController: NavController,
    viewModel: MyBodyViewModel = getViewModel()
) {
    val state: MyBodyState by remember { viewModel.state }

    LaunchedEffect(Unit) {
        viewModel.accept(InitScreen(navController))
    }

    when (state) {
        Loading -> {
            LoadingBar(
                modifier = Modifier
                    .fillMaxSize()
                    .background(DarkGray),
                color = White
            )
        }

        is Content -> {
            if ((state as Content).showChangeStatAlertDialog) {
                ChangeStatAlertDialog(
                    type = (state as Content).type ?: BodyParameter.WEIGHT,
                    value = (state as Content).parameterValue ?: "",
                    onValueChange = { viewModel.accept(DoEditBodyParameter(it, (state as Content).type ?: BodyParameter.WEIGHT)) },
                    onDismiss = { viewModel.accept(OnDismissChangeAlertDialog) }
                ) { viewModel.accept(OnAcceptChangeAlertDialog) }
            }

            if ((state as Content).showPickPhotoAlertDialog) {
                PickPhotoAlertDialog(
                    onImagePick = { viewModel.accept(OnSelectPhoto(it)) }
                ) {  viewModel.accept(OnDismissPickPhotoAlertDialog) }
            }

            if ((state as Content).error != null) {
                ErrorDialog(error = listOf((state as Content).error ?: R.string.something_went_wrong)) {
                    viewModel.accept(OnDismissErrorDialog)
                }
            }

            MyBodyContent(
                (state as Content).weight,
                (state as Content).height,
                (state as Content).photoList,
                viewModel
            )
        }
    }
}

@Composable
fun MyBodyContent(
    weight: Int? = null,
    height: Int? = null,
    photoList: List<Pair<Bitmap?, String?>>,
    viewModel: MyBodyViewModel
) {
    Column(
        Modifier
            .fillMaxSize()
            .scrollable(rememberScrollState(), Orientation.Vertical)
            .background(DarkGray),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(R.string.my_body),
            modifier = Modifier
                .padding(start = 16.dp, top = 48.dp),
            style = MaterialTheme.typography.h5,
            color = White
        )

        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 48.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            BodyStat(
                type = BodyParameter.WEIGHT, value = weight
            ) { viewModel.accept(OnEditParameterClick(BodyParameter.WEIGHT)) }

            BodyStat(
                type = BodyParameter.HEIGHT, value = height
            ) { viewModel.accept(OnEditParameterClick(BodyParameter.HEIGHT)) }
        }

        Row(
            Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 12.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.my_progress),
                modifier = Modifier.align(Alignment.CenterVertically),
                color = White,
                style = MaterialTheme.typography.h5
            )

            Text(
                text = stringResource(R.string.see_all),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { viewModel.accept(OnSeeAllClick) },
                color = LightGray,
                style = MaterialTheme.typography.caption
            )
        }

        ProgressPhotos(
            imageBefore = photoList[0].first,
            dateBefore = photoList[0].second,
            imageAfter = photoList[1].first,
            dateAfter = photoList[1].second
        ) {
            viewModel.accept(OnAddPhotoClick)
        }

        Spacer(modifier = Modifier.height(37.dp))

        ButtonWithArrowRight(text = R.string.train_progress) { viewModel.accept(OnTrainProgressClick) }
        Spacer(modifier = Modifier.height(4.dp))
        ButtonWithArrowRight(text = R.string.statistics) { viewModel.accept(OnStatisticsClick) }
    }
}

@Preview
@Composable
fun MyBodyScreenPreview() {
    val navController = rememberNavController()
    MyBodyScreen(navController = navController)
}