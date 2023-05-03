package com.example.team33.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ElectricBolt
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Kitchen
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
    ELECTRICITY(
        route = "electricity",
        selectedIcon = Icons.Rounded.ElectricBolt,
        // unselectedIcon = Icons.Rounded.ElectricBolt,
        titleTextId = R.string.electricity,
    ),
    APPLIANCES(
        route = "appliances",
        selectedIcon =  Icons.Rounded.Kitchen,
        // unselectedIcon = Icons.Rounded.Kitchen,
        titleTextId = R.string.appliances,
    ),
}