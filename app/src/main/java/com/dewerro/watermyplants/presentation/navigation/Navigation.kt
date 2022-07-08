package com.dewerro.watermyplants.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dewerro.watermyplants.domain.model.Plant
import com.dewerro.watermyplants.presentation.views.CameraScreen
import com.dewerro.watermyplants.presentation.views.PlantListScreen
import com.dewerro.watermyplants.presentation.views.PlantScreen
import com.dewerro.watermyplants.presentation.views.SetPlantScreen
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Composable
fun Navigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBottomNavigation(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "${Screen.PlantScreen.route}/{plantJsonString}",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(
                route = "${Screen.PlantScreen.route}/{plantJsonString}",
                arguments = listOf(navArgument("plantJsonString") {
                    type = NavType.StringType; nullable = true
                })
            ) { navBackStackEntry ->
                val jsonString =
                    navBackStackEntry.arguments?.getString("plantJsonString")?.replace('$', '/')
                val plant: Plant? = jsonString?.let { Json.decodeFromString(it) }

                PlantScreen(plant)
            }
            composable(Screen.ListScreen.route) { PlantListScreen(navController) }
            composable(Screen.SetPlantScreen.route) { SetPlantScreen(navController) }
            composable(Screen.CameraScreen.route) { CameraScreen(navController) }
        }
    }
}