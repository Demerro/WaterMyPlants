package com.dewerro.watermyplants.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dewerro.watermyplants.R
import com.dewerro.watermyplants.presentation.MainViewModel
import com.dewerro.watermyplants.presentation.navigation.Screen
import com.dewerro.watermyplants.presentation.theme.Shapes
import com.dewerro.watermyplants.presentation.theme.Typography

@Composable
fun ListScreen(
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colors.primary,
                onClick = { navController.navigate(Screen.SetPlantScreen.route) }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Icon"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

            }
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth(),
                columns = GridCells.Fixed(2),
                content = {
                    items(10) {
                        PlantListItem(
                            modifier = Modifier.padding(10.dp),
                            imageId = R.drawable.cactus,
                            title = "Cactus"
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun CategoryRowItem(modifier: Modifier = Modifier, title: String, onClick: () -> Unit) {
    TextButton(modifier = modifier, onClick = onClick) {
        Text(
            text = title,
            color = MaterialTheme.colors.primary,
            style = Typography.h4
        )
    }
}

@Composable
fun PlantListItem(
    modifier: Modifier = Modifier,
    imageId: Int,
    title: String,
    description: String = ""
) {
    Card(
        modifier = modifier,
        shape = Shapes.medium
    ) {
        Column {
            Image(
                painter = painterResource(imageId),
                contentDescription = "Plant Image",
                modifier = Modifier.clip(Shapes.medium)
            )
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = title,
                    color = MaterialTheme.colors.primary,
                    style = Typography.h3
                )
                if (description.isNotEmpty()) {
                    Text(
                        text = description,
                        color = MaterialTheme.colors.secondary,
                        style = Typography.h5
                    )
                }
            }
        }
    }
}