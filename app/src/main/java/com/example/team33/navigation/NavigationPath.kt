package com.example.team33.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Kitchen
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.team33.R

enum class NavigationPath(
    val route: String,
    val selectedIcon: ImageVector,
    val titleTextId: Int,
) {
    // Home screen element
    HOME(
        route = "home",
        selectedIcon = Icons.Rounded.Home,
        titleTextId = R.string.home,
    ),

    // Appliances screen element
    APPLIANCES(
        route = "appliances",
        selectedIcon = Icons.Rounded.Kitchen,
        titleTextId = R.string.appliances,
    ),

    // Settings screen element
    SETTINGS(
        route = "settings",
        selectedIcon = Icons.Rounded.Settings,
        titleTextId = R.string.settings,
    ),
}