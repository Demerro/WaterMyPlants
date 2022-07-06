package com.dewerro.watermyplants.presentation.views

import android.app.TimePickerDialog
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.PhotoLibrary
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dewerro.watermyplants.R
import com.dewerro.watermyplants.presentation.MainViewModel
import com.dewerro.watermyplants.presentation.components.CameraCapture
import com.dewerro.watermyplants.presentation.components.GallerySelect
import com.dewerro.watermyplants.presentation.navigation.Screen
import com.dewerro.watermyplants.presentation.theme.Shapes
import com.dewerro.watermyplants.presentation.theme.Typography
import com.dewerro.watermyplants.presentation.utils.GetOnceResult
import java.util.*

@Composable
fun SetPlantScreen(viewModel: MainViewModel, navController: NavHostController) {
    val context = LocalContext.current

    val title = remember { mutableStateOf("") }
    val humidity = remember { mutableStateOf("") }
    val temperature = remember { mutableStateOf("") }
    val category = remember { mutableStateOf("") }
    val plant = remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val time = remember { mutableStateOf("") }

    val timePickerDialog = TimePickerDialog(context, { _, mHour: Int, mMinute: Int ->
        time.value = "$mHour:$mMinute"
    }, hour, minute, true)

    val photoUri = remember { mutableStateOf<Uri?>(null) }
    navController.GetOnceResult<Uri>("photo_file_uri") {
        photoUri.value = it
    }

    var showGallerySelect by remember { mutableStateOf(false) }
    if (showGallerySelect) {
        GallerySelect {
            photoUri.value = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 15.dp)
    ) {
        Text(
            text = stringResource(R.string.add_new_plant),
            style = Typography.h1,
            modifier = Modifier.padding(top = 15.dp, bottom = 10.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                PlantField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    imageVector = Icons.Default.Title,
                    labelText = stringResource(R.string.title),
                    modifier = Modifier.fillMaxWidth()
                )
                Row {
                    Box(
                        modifier = Modifier
                            .width(170.dp)
                            .height(290.dp)
                            .padding(top = 15.dp, end = 15.dp)
                            .clip(Shapes.medium),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        if (photoUri.value != null) {
                            AsyncImage(
                                model = photoUri.value,
                                contentDescription = "Plant Photo",
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            CameraCapture(
                                modifier = Modifier.clickable {
                                    navController.navigate(Screen.CameraScreen.route)
                                }
                            )
                        }
                        FloatingActionButton(
                            onClick = {
                                showGallerySelect = true
                            },
                            shape = RoundedCornerShape(topStart = 10.dp),
                            backgroundColor = MaterialTheme.colors.primary
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.PhotoLibrary,
                                contentDescription = "Image Icon"
                            )
                        }
                    }
                    Column {
                        DropDownMenu(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp)
                                .height(55.dp),
                            stringArray = stringArrayResource(R.array.sizes),
                            imageVector = Icons.Default.Straighten
                        )
                        PlantField(
                            value = humidity.value,
                            onValueChange = { if (it.length <= 3) humidity.value = it },
                            imageVector = Icons.Default.WaterDrop,
                            labelText = stringResource(R.string.humidity),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        )
                        DropDownMenu(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp)
                                .height(55.dp),
                            stringArray = stringArrayResource(R.array.lights),
                            imageVector = Icons.Default.WbSunny
                        )
                        PlantField(
                            value = temperature.value,
                            onValueChange = { temperature.value = it },
                            imageVector = Icons.Default.Thermostat,
                            labelText = stringResource(R.string.temperature),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp)
                        )
                    }
                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Column(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PlantField(
                        value = category.value,
                        onValueChange = { category.value = it },
                        imageVector = Icons.Default.Category,
                        labelText = stringResource(R.string.category),
                        modifier = Modifier.width(160.dp)
                    )
                    PlantField(
                        value = plant.value,
                        onValueChange = { plant.value = it },
                        imageVector = Icons.Default.Grass,
                        labelText = stringResource(R.string.plant),
                        modifier = Modifier.width(160.dp)
                    )
                }
                OutlinedButton(
                    onClick = { timePickerDialog.show() },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = Shapes.medium,
                    border = BorderStroke(1.dp, MaterialTheme.colors.secondary.copy(0.2f))
                ) {
                    Text(
                        text = stringResource(R.string.set_watering_interval),
                        style = Typography.h4,
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                onClick = { navController.navigateUp() },
                border = BorderStroke(1.dp, MaterialTheme.colors.secondary.copy(0.2f)),
                modifier = Modifier.width(150.dp)
            ) {
                Text(
                    text = stringResource(R.string.cancel),
                    style = Typography.h4,
                    color = MaterialTheme.colors.secondary
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.width(150.dp)
            ) {
                Text(
                    text = stringResource(R.string.add_plant),
                    style = Typography.h4
                )
            }
        }
    }
}

@Composable
fun PlantField(
    value: String,
    onValueChange: (String) -> Unit,
    imageVector: ImageVector,
    labelText: String,
    modifier: Modifier = Modifier
) {
    val focus = LocalFocusManager.current

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = imageVector,
                contentDescription = "Plant Field Icon"
            )
        },
        label = {
            Text(
                text = labelText,
                style = Typography.h4
            )
        },
        singleLine = true,
        shape = Shapes.medium,
        modifier = modifier,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            leadingIconColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = MaterialTheme.colors.secondary.copy(0.2f)
        ),
        keyboardActions = KeyboardActions(onDone = { focus.moveFocus(FocusDirection.Down) })
    )
}

@Composable
fun DropDownMenu(
    modifier: Modifier = Modifier,
    stringArray: Array<String>,
    imageVector: ImageVector
) {
    var expandedState by remember { mutableStateOf(false) }

    var text by remember { mutableStateOf(stringArray[0]) }

    Box {
        OutlinedButton(
            onClick = { expandedState = true },
            modifier = modifier,
            shape = Shapes.medium,
            border = BorderStroke(1.dp, MaterialTheme.colors.secondary.copy(0.2f))
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = "DropDownMenu Item Icon"
                )
                Text(
                    text = text,
                    style = Typography.h4,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
        DropdownMenu(
            expanded = expandedState,
            onDismissRequest = { expandedState = false }
        ) {
            repeat(stringArray.size) { index ->
                if (index != 0) {
                    Divider(modifier = Modifier.padding(start = 10.dp, end = 10.dp))
                }
                DropdownMenuItem(onClick = {
                    text = stringArray[index]
                    expandedState = false
                }) {
                    Text(stringArray[index])
                }
            }
        }
    }
}