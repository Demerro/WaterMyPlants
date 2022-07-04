package com.dewerro.watermyplants.presentation.views

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dewerro.watermyplants.R
import com.dewerro.watermyplants.presentation.theme.Shapes
import com.dewerro.watermyplants.presentation.theme.Typography

@Composable
fun PlantScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = "Cereus Cactus",
            modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
            style = Typography.h1
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.cactus),
                contentDescription = "Plant Image",
                modifier = Modifier
                    .width(200.dp)
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
                        value = "Small"
                    )
                    Divider()
                    PlantParameter(
                        modifier = Modifier.fillMaxWidth(),
                        imageVector = Icons.Default.WaterDrop,
                        description = stringResource(R.string.humidity),
                        value = "64%"
                    )
                    Divider()
                    PlantParameter(
                        modifier = Modifier.fillMaxWidth(),
                        imageVector = Icons.Default.WbSunny,
                        description = stringResource(R.string.light),
                        value = "Diffuse"
                    )
                    Divider()
                    PlantParameter(
                        modifier = Modifier.fillMaxWidth(),
                        imageVector = Icons.Default.Thermostat,
                        description = stringResource(R.string.temperature),
                        value = "18-22 C"
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
                        value = "Indoor"
                    )
                    VerticalDivider()
                    PlantParameter(
                        description = stringResource(R.string.plant),
                        value = "Cactus"
                    )
                    VerticalDivider()
                    PlantParameter(
                        description = stringResource(R.string.watering),
                        value = "Evey 8 hours"
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