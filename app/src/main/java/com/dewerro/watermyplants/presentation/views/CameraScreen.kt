package com.dewerro.watermyplants.presentation.views

import android.net.Uri
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import com.dewerro.watermyplants.presentation.components.CameraCapture
import com.dewerro.watermyplants.presentation.components.takePicture
import com.dewerro.watermyplants.presentation.utils.executor
import kotlinx.coroutines.launch

@Composable
fun CameraScreen(navController: NavHostController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val buttonClickableState = remember { mutableStateOf(true) }

    val imageCaptureUseCase by remember {
        mutableStateOf(
            ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .build()
        )
    }

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (photo, back) = createRefs()

        CameraCapture(imageCaptureUseCase = imageCaptureUseCase)
        IconButton(
            onClick = { navController.navigateUp() },
            modifier = Modifier
                .padding(end = 30.dp)
                .constrainAs(back) {
                    end.linkTo(photo.start)
                    top.linkTo(photo.top)
                }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back Icon",
                tint = MaterialTheme.colors.secondaryVariant
            )
        }
        FloatingActionButton(
            onClick = {
                if (buttonClickableState.value) {
                    buttonClickableState.value = false

                    var photoFileUri: Uri? = null
                    coroutineScope.launch {
                        photoFileUri = imageCaptureUseCase.takePicture(context.executor).toUri()
                    }.invokeOnCompletion {
                        navController.previousBackStackEntry?.savedStateHandle?.set(
                            "photo_file_uri",
                            photoFileUri
                        )
                        navController.navigateUp()
                    }
                }
            },
            modifier = Modifier
                .padding(bottom = 50.dp)
                .constrainAs(photo) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(
                imageVector = Icons.Outlined.PhotoCamera,
                contentDescription = "Photo Icon"
            )
        }
    }
}