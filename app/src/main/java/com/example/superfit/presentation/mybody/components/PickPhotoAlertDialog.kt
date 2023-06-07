package com.example.superfit.presentation.mybody.components

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superfit.R
import com.example.superfit.presentation.common.extensions.getOutputUri
import com.example.superfit.presentation.common.extensions.toByteArray
import com.example.superfit.presentation.ui.theme.AlertDialogGray
import com.example.superfit.presentation.ui.theme.Purple
import com.example.superfit.presentation.ui.theme.White
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PickPhotoAlertDialog(
    onImagePick: (ByteArray) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    val imageUri = getOutputUri(context)
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { onImagePick(it.toByteArray(context)) }
        }
    )

    val singleMakePhotoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success ->
            if (success) {
                onImagePick(imageUri.toByteArray(context))
            }
        }
    )

    AlertDialog(
        onDismissRequest = { onDismiss() },
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        title = {
            Text(
                text = stringResource(R.string.choose_image),
                modifier = Modifier,
                style = MaterialTheme.typography.body2,
                color = White
            )
        },
        buttons = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(
                    onClick = { singlePhotoPickerLauncher.launch("image/*") }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_insert_photo),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp).size(52.dp),
                        tint = Purple
                    )
                }

                IconButton(
                    onClick = {
                        when (cameraPermissionState.hasPermission) {
                            true  -> singleMakePhotoLauncher.launch(imageUri)
                            false -> cameraPermissionState.launchPermissionRequest()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_add_a_photo),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp).size(52.dp),
                        tint = Purple
                    )
                }
            }
        },
        backgroundColor = AlertDialogGray
    )

}