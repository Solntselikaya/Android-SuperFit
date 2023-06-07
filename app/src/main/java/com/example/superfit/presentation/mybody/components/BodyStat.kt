@file:OptIn(ExperimentalMaterialApi::class)

package com.example.superfit.presentation.mybody.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.ui.theme.LightGray
import com.example.superfit.presentation.ui.theme.White

@Composable
fun BodyStat(
    type: BodyParameter,
    value: Int? = null,
    onEditClick: () -> Unit
) {
    val text = when {
        type == BodyParameter.HEIGHT && value != null -> stringResource(
            R.string.my_height,
            value.toString()
        )

        type == BodyParameter.WEIGHT && value != null -> stringResource(
            R.string.my_weight,
            value.toString()
        )

        else -> stringResource(R.string.undefined)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onEditClick() }
    ) {
        Text(
            text = text,
            modifier = Modifier,
            style = MaterialTheme.typography.h4,
            color = White
        )
        Text(
            text = stringResource(R.string.edit),
            modifier = Modifier
                .padding(top = 4.dp),
            color = LightGray,
            style = MaterialTheme.typography.caption
        )
    }
}