package com.example.team33.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.team33.navigation.TopLevelDestination
import com.example.team33.ui.screens.*
import com.example.team33.ui.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(windowSizeClass: WindowSizeClass, viewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()

    Scaffold(bottomBar = { BottomBar(navController) }) { innerPadding ->
        NavHost(
            navController,
            startDestination = TopLevelDestination.HOME.route,
            Modifier.padding(innerPadding)
        ) {
            composable(TopLevelDestination.HOME.route) { HomeScreen(viewModel = viewModel) }
            composable(TopLevelDestination.ELECTRICITY.route) { ElectricityScreen(viewModel = viewModel) }
            composable(TopLevelDestination.APPLIANCES.route) { AppliancesScreen(viewModel = viewModel) }
            composable(TopLevelDestination.SETTINGS.route) { SettingsScreen(viewModel = viewModel, navController) }
            composable(route = "openSource") { OpenSource() }
            composable(route = "showDeveloper") { ShowDevelopers() }
            composable(route = "showPurpose") { ShowPurpose() }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(){
        TopLevelDestination.values().forEach { screen ->
            AddNavItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}



@Composable
fun RowScope.AddNavItem(
    screen: TopLevelDestination,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(icon = {
        Icon(
            imageVector = screen.selectedIcon,
            contentDescription = stringResource(id = screen.titleTextId)
        )
    },
        label = { Text(stringResource(id = screen.titleTextId)) },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = false
                }
                launchSingleTop = true
                restoreState = true
            }
        })
}
