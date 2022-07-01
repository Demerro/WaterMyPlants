package com.dewerro.watermyplants.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Compost
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Park
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(
    val icon: ImageVector,
    val navRoute: String
) {
    object Home : NavItem(Icons.Default.Compost, Screen.PlantScreen.route)
    object List : NavItem(Icons.Default.List, Screen.ListScreen.route)
}