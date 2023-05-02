package com.example.team33.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.team33.R
import com.example.team33.ui.uistates.MainUiState
import com.example.team33.ui.viewmodels.MainViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf

// Displays the electricity price of chosen region for the current day
@Composable
fun ElectricityScreen(
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToElectricityScreen: () -> Unit,
    onNavigateToAppliancesScreen: () -> Unit,
    windowSize: WindowWidthSizeClass,
    mainViewModel: MainViewModel,
    mainUiState: MainUiState,
    modifier: Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        // TODO: Implement adaptive layout
        if (windowSize == WindowWidthSizeClass.Compact) {

        }
        if (windowSize == WindowWidthSizeClass.Medium) {

        }
        if (windowSize == WindowWidthSizeClass.Expanded) {

        }

        Text(text = "Electricity Screen", fontSize = 40.sp)

        Text(text = stringResource(id = R.string.selected_region) + ": ${mainUiState.currentRegion}")

        // FIXME: Don't show a graph if there was an error fetching data from API
        if (mainUiState.electricityPrices != null) {
            ShowElectricityGraph(mainUiState.electricityPrices!!)
        }

        // Calls on a function from HomeScreen.kt that lets user navigate to different screens
        NavigateScreensComposable(
            onNavigateToHomeScreen = onNavigateToHomeScreen,
            onNavigateToElectricityScreen = onNavigateToElectricityScreen,
            onNavigateToAppliancesScreen = onNavigateToAppliancesScreen,
        )
    }
}

@Composable
fun ShowElectricityGraph(list: List<Double>) {
    val chartEntryModelProducer = entryModelOf(List(list.size) { FloatEntry(it.toFloat(), list[it].toFloat()) })

    Chart(
        chart = lineChart(),
        model = chartEntryModelProducer,
        startAxis = startAxis(),
        bottomAxis = bottomAxis(),
    )
}