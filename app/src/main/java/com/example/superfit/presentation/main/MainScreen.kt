package com.example.superfit.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.superfit.R
import com.example.superfit.presentation.common.ExerciseCard
import com.example.superfit.presentation.common.TopImage
import com.example.superfit.presentation.main.components.MyBodyCard
import com.example.superfit.presentation.ui.theme.Black
import com.example.superfit.presentation.ui.theme.Gray
import com.example.superfit.presentation.ui.theme.White

@Composable
fun MainScreen(navController: NavController) {

    Column(
        Modifier
            .fillMaxSize()
            .background(White)
            .verticalScroll(rememberScrollState())
    ) {
        TopImage() {}

        Text(
            text = stringResource(R.string.my_body),
            modifier = Modifier.padding(start = 16.dp),
            color = Black,
            style = MaterialTheme.typography.h5
        )

        MyBodyCard() {}

        Row(
            Modifier
                .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.last_exercises),
                modifier = Modifier.align(Alignment.CenterVertically),
                color = Black,
                style = MaterialTheme.typography.h5
            )

            Text(
                text = stringResource(R.string.see_all),
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { },
                color = Gray,
                style = MaterialTheme.typography.caption
            )
        }

        ExerciseCard(
            image = painterResource(R.drawable.push_ups_image),
            name = stringResource(R.string.push_ups),
            description = stringResource(R.string.plank_description)
        ) {  }
        ExerciseCard(
            image = painterResource(R.drawable.plank_image),
            name = stringResource(R.string.plank),
            description = stringResource(R.string.plank_description)
        ) {  }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(start = 16.dp)
                .navigationBarsPadding()
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_left),
                contentDescription = null,
                tint = Black
            )

            Text(
                text = stringResource(R.string.sign_out),
                modifier = Modifier,
                style = MaterialTheme.typography.h5,
                color = Black
            )
        }
    }
}