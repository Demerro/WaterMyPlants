package com.dewerro.watermyplants.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    PlantParameter("Size", "Small")
                    PlantParameter("Humidity", "64%")
                    PlantParameter("Light", "Diffuse")
                    PlantParameter("Temperature", "18-22 C")
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
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    PlantParameter("Category", "Indoor")
                    PlantParameter("Plant", "Cactus")
                    PlantParameter("Watering", "Evey 8 hours")
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
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(
                    text = "About",
                    style = Typography.h2
                )
                Text(
                    text = "A type of desert plant that has thick, leafless stems covered in prickly spines or sharp spikes. Cactus plants are able to thrive in dry climates because they store water in their stems. Some large cactus varieties can store an impressive amount of water.",
                    color = MaterialTheme.colors.secondary,
                    style = Typography.h4
                )
            }
        }
    }
}

@Composable
fun PlantParameter(description: String, value: String) {
    Row {
        Column {
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