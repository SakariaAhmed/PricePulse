package com.example.team33.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Kitchen
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.team33.R

enum class TopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    // val unselectedIcon: ImageVector,
    val titleTextId: Int,
) {
    HOME(
        route = "home",
        selectedIcon = Icons.Rounded.Home,
        // unselectedIcon = Icons.Rounded.Home,
        titleTextId = R.string.home,
    ),
    APPLIANCES(
        route = "appliances",
        selectedIcon =  Icons.Rounded.Kitchen,
        // unselectedIcon = Icons.Rounded.Kitchen,
        titleTextId = R.string.appliances,
    ),
    SETTINGS(
        route = "settings",
        selectedIcon =  Icons.Rounded.Settings,
        // unselectedIcon = Icons.Rounded.Settings,
        titleTextId = R.string.settings,
    ),
}