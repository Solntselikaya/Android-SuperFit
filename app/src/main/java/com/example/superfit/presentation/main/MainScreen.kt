package com.example.superfit.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
    ) {
        TopImage(false) {}

        Text(
            text = stringResource(R.string.my_body),
            color = Black,
            style = MaterialTheme.typography.h5
        )

        MyBodyCard() {}

        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.last_exercises),
                modifier = Modifier
                    .padding(top = 8.dp),
                color = Black,
                style = MaterialTheme.typography.h5
            )

            TextButton(
                onClick = { /*TODO*/ }
            ) {
                Text(
                    text = stringResource(R.string.see_all),
                    color = Gray,
                    style = MaterialTheme.typography.caption
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = { /*TODO*/ }
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