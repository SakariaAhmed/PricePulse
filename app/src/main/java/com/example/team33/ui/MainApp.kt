package com.example.team33.ui

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.team33.R
import com.example.team33.navigation.TopLevelDestination
import com.example.team33.network.ElectricityRegion
import com.example.team33.ui.screens.*
import com.example.team33.ui.theme.Team33Theme
import com.example.team33.ui.uistates.MainUiState
import com.example.team33.ui.viewmodels.MainViewModel
import kotlin.math.sin

// To create data points for previewing
fun genSineWaveList(size: Int): List<Double> {
    return List(size) { 1 + sin(3 * Math.PI * 5 * size / 4 * it / size) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = viewModel()) {
    val navController = rememberNavController()
    val mainUiState by viewModel.uiState.collectAsState()
    
    AppScreenUI(
        navController = navController,
        mainUiState = mainUiState,
        changeElectricityRegion = { viewModel.changeElectricityRegion(it) },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppScreenUI(
    navController: NavHostController,
    mainUiState: MainUiState,
    changeElectricityRegion: (ElectricityRegion) -> Unit,
    modifier: Modifier = Modifier,
) {
    var topBarScreenTitleId by remember { mutableStateOf(TopLevelDestination.HOME.titleTextId) }
    Scaffold(topBar = {
        LargeTopAppBar(
            title = {
                Text(
                    text = stringResource(id = topBarScreenTitleId),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.displayLarge
                )
            },
            colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
        )
    }, bottomBar = { BottomBar(navController) }, modifier = modifier) { innerPadding ->
        NavHost(
            navController,
            startDestination = TopLevelDestination.HOME.route,
            Modifier.padding(innerPadding)
        ) {
            composable(TopLevelDestination.HOME.route) {
                topBarScreenTitleId = TopLevelDestination.HOME.titleTextId
                HomeScreen(mainUiState = mainUiState, modifier = Modifier.padding(start = 10.dp))
            }
            composable(TopLevelDestination.APPLIANCES.route) {
                topBarScreenTitleId = TopLevelDestination.APPLIANCES.titleTextId
                AppliancesScreen(mainUiState = mainUiState)
            }
            composable(TopLevelDestination.SETTINGS.route) {
                topBarScreenTitleId = TopLevelDestination.SETTINGS.titleTextId
                SettingsScreen(
                    mainUiState = mainUiState,
                    navController = navController,
                    changeElectricityRegion = { changeElectricityRegion(it) },
                )
            }
            composable(route = "openSource") {
                topBarScreenTitleId = R.string.opensource
                OpenSource()
            }
            composable(route = "showDeveloper") {
                topBarScreenTitleId = R.string.developers
                ShowDevelopers()
            }
            composable(route = "showPurpose") {
                topBarScreenTitleId = R.string.usage
                ShowPurpose()
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
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
    navController: NavHostController,
    modifier: Modifier = Modifier
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
        }, modifier = modifier
    )
}

@Preview(name = "Light", uiMode = UI_MODE_NIGHT_NO)
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun AppScreenPreview() {
    Team33Theme(dynamicColor = false) {
        Surface {
            AppScreenUI(
                rememberNavController(),
                MainUiState(electricityPrices = genSineWaveList(24)),
                {}
            )
        }
    }
}