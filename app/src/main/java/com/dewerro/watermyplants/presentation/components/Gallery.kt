package com.dewerro.watermyplants.presentation.components

import android.Manifest
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GallerySelect(
    onImageUri: (Uri) -> Unit = { }
) {
    val emptyImageUri: Uri = Uri.parse("file://dewerro/null")

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            onImageUri(uri ?: emptyImageUri)
        }
    )

    @Composable
    fun LaunchGallery() {
        SideEffect {
            launcher.launch("image/*")
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val permissionState = rememberPermissionState(Manifest.permission.ACCESS_MEDIA_LOCATION)

        LaunchedEffect(permissionState) {
            permissionState.launchPermissionRequest()
        }
    }

    LaunchGallery()
}