package com.dewerro.watermyplants.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compost
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(
    val icon: ImageVector,
    val navRoute: String
) {
    object Home : NavItem(Icons.Default.Compost, "${Screen.PlantScreen.route}/{plantJsonString}")
    object List : NavItem(Icons.Default.List, Screen.ListScreen.route)
}