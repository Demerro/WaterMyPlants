package com.dewerro.watermyplants.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dewerro.watermyplants.presentation.views.ListScreen
import com.dewerro.watermyplants.presentation.views.PlantScreen
import com.dewerro.watermyplants.presentation.views.SetPlantScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomNavigation(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.PlantScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.PlantScreen.route) { PlantScreen() }
            composable(Screen.ListScreen.route) { ListScreen(viewModel(), navController) }
            composable(Screen.SetPlantScreen.route) { SetPlantScreen(navController) }
        }
    }
}