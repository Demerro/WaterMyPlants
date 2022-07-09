package com.dewerro.watermyplants.presentation.views

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dewerro.watermyplants.R
import com.dewerro.watermyplants.presentation.MainEvent
import com.dewerro.watermyplants.presentation.MainViewModel
import com.dewerro.watermyplants.presentation.navigation.Screen
import com.dewerro.watermyplants.presentation.theme.Shapes
import com.dewerro.watermyplants.presentation.theme.Typography
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlantListScreen(
    navController: NavHostController, viewModel: MainViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value

    val filterTitle = remember { mutableStateOf("") }
    val filteredPlants = state.plantList.filter {
        if (filterTitle.value.isEmpty()) true
        else it.category == filterTitle.value
    }

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
        if (state.plantList.isNotEmpty()) {
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
                    item {
                        CategoryRowItem(title = stringResource(R.string.all)) {
                            filterTitle.value = ""
                        }
                    }
                    items(state.categoryList.size) {
                        CategoryRowItem(title = state.categoryList[it]) {
                            filterTitle.value = state.categoryList[it]
                        }
                    }
                }
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Fixed(2),
                    content = {
                        items(
                            filteredPlants.size,
                            { index -> filteredPlants[index].hashCode() }) { index ->
                            val dismissState = rememberDismissState(
                                confirmStateChange = {
                                    if (it == DismissValue.DismissedToEnd) {
                                        viewModel.onEvent(MainEvent.DeletePlant(filteredPlants[index]))
                                    }
                                    true
                                }
                            )

                            PlantListItem(
                                dismissState = dismissState,
                                imageUri = filteredPlants[index].photoUriString.toUri(),
                                modifier = Modifier
                                    .clickable {
                                        navController.navigate(
                                            Screen.PlantScreen.route +
                                                    "/${
                                                        Json
                                                            .encodeToString(filteredPlants[index])
                                                            .replace('/', '$')
                                                    }"
                                        )
                                    }
                                    .padding(10.dp),
                                type = filteredPlants[index].type,
                                plant = filteredPlants[index].plant
                            )
                        }
                    }
                )
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.this_is_where_we_will_store_the_plants),
                    style = Typography.h3,
                    color = MaterialTheme.colors.primary
                )
            }
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlantListItem(
    modifier: Modifier = Modifier,
    dismissState: DismissState,
    imageUri: Uri,
    type: String,
    plant: String
) {
    SwipeToDismiss(
        state = dismissState,
        modifier = modifier,
        background = {
            Card(
                modifier = Modifier.fillMaxSize(),
                backgroundColor = MaterialTheme.colors.primary,
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Icon",
                    tint = MaterialTheme.colors.secondaryVariant
                )
            }
        },
        dismissContent = {
            Card(
                shape = Shapes.medium
            ) {
                Column {
                    AsyncImage(
                        model = imageUri,
                        contentDescription = "Plant Image",
                        modifier = Modifier.clip(Shapes.medium)
                    )
                    Column(
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = "$plant $type",
                            color = MaterialTheme.colors.primary,
                            style = Typography.h3
                        )
                    }
                }
            }
        }
    )
}