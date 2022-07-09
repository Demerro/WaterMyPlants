package com.dewerro.watermyplants.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dewerro.watermyplants.R
import com.dewerro.watermyplants.domain.model.Plant
import com.dewerro.watermyplants.presentation.MainViewModel
import com.dewerro.watermyplants.presentation.theme.Shapes
import com.dewerro.watermyplants.presentation.theme.Typography

@Composable
fun PlantScreen(plant: Plant?, viewModel: MainViewModel = hiltViewModel()) {

    val state = viewModel.state.value

    val _plant = plant ?: if (state.plantList.isNotEmpty())
        state.plantList.random()
    else
        null


    if (_plant != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(
                text = "${_plant.plant} ${_plant.type}",
                modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                style = Typography.h1
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                AsyncImage(
                    model = _plant.photoUriString.toUri(),
                    contentDescription = "Plant Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(170.dp)
                        .clip(Shapes.medium)
                )
                Card(
                    modifier = Modifier.padding(start = 20.dp),
                    shape = Shapes.medium
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        PlantParameter(
                            modifier = Modifier.fillMaxWidth(),
                            imageVector = Icons.Default.Straighten,
                            description = stringResource(R.string.size),
                            value = _plant.size
                        )
                        Divider()
                        PlantParameter(
                            modifier = Modifier.fillMaxWidth(),
                            imageVector = Icons.Default.WaterDrop,
                            description = stringResource(R.string.humidity),
                            value = "${_plant.humidity}%"
                        )
                        Divider()
                        PlantParameter(
                            modifier = Modifier.fillMaxWidth(),
                            imageVector = Icons.Default.WbSunny,
                            description = stringResource(R.string.light),
                            value = _plant.light
                        )
                        Divider()
                        PlantParameter(
                            modifier = Modifier.fillMaxWidth(),
                            imageVector = Icons.Default.Thermostat,
                            description = stringResource(R.string.temperature),
                            value = "${_plant.temperature}°"
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                shape = Shapes.medium
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        PlantParameter(
                            description = stringResource(R.string.category),
                            value = _plant.category
                        )
                        VerticalDivider()
                        PlantParameter(
                            description = stringResource(R.string.plant),
                            value = _plant.plant
                        )
                        VerticalDivider()
                        PlantParameter(
                            description = stringResource(R.string.watering),
                            value = _plant.wateringTime
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 20.dp),
                shape = Shapes.medium
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        text = stringResource(R.string.random_fact),
                        style = Typography.h2
                    )
                    Text(
                        text = stringArrayResource(R.array.facts).random(),
                        color = MaterialTheme.colors.secondary,
                        style = Typography.h4
                    )
                }
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.your_plant_will_be_displayed_here),
                style = Typography.h3,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
fun PlantParameter(
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    description: String,
    value: String
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        if (imageVector != null) {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colors.background, Shapes.medium)
                    .clip(Shapes.small)
                    .size(30.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = "Parameter Icon",
                    tint = MaterialTheme.colors.primary
                )
            }
        }
        Column(
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(
                text = description,
                color = MaterialTheme.colors.secondary,
                style = Typography.h5
            )
            Text(
                text = value,
                color = MaterialTheme.colors.primary,
                style = Typography.h3
            )
        }
    }
}

private const val DividerAlpha = 0.12f

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface.copy(alpha = DividerAlpha),
    thickness: Dp = 1.dp
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(thickness)
            .background(color = color)
    )
}