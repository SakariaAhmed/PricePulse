package com.example.team33.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElectricityScreen(
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToForecastScreen: () -> Unit,
    onNavigateToElectricityScreen: () -> Unit,
    windowSize: WindowWidthSizeClass,
    mainViewModel: MainViewModel,
    mainUiState: MainUiState,
    modifier: Modifier
) {
    // TODO: Implement adaptive layout
    if (windowSize == WindowWidthSizeClass.Compact) {

    }
    if (windowSize == WindowWidthSizeClass.Medium) {

    }
    if (windowSize == WindowWidthSizeClass.Expanded) {

    }

    var expanded by remember { mutableStateOf(false) }
    var selectedRegion by remember { mutableStateOf(mainUiState.selectedRegion) }
    val regionOptions: List<String> = listOf(
        stringResource(id = R.string.east_norway),
        stringResource(id = R.string.south_norway),
        stringResource(id = R.string.mid_norway),
        stringResource(id = R.string.north_norway),
        stringResource(id = R.string.west_norway)
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Electricity Screen", fontSize = 40.sp)

        // TODO: and use a for loop for dropwdownmenu-items

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {expanded = it}
        ) {
            TextField(
                value = selectedRegion,
                onValueChange = {},
                label = { Text(stringResource(R.string.select_region)) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "East-Norway") },
                    onClick = {
                        selectedRegion = "East-Norway"
                        mainUiState.selectedRegion = "East-Norway"
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "South-Norway") },
                    onClick = {
                        selectedRegion = "South-Norway"
                        mainUiState.selectedRegion = "South-Norway"
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Mid-Norway") },
                    onClick = {
                        selectedRegion = "Mid-Norway"
                        mainUiState.selectedRegion = "Mid-Norway"
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "North-Norway") },
                    onClick = {
                        selectedRegion = "North-Norway"
                        mainUiState.selectedRegion = "North-Norway"
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "West-Norway") },
                    onClick = {
                        selectedRegion = "West-Norway"
                        mainUiState.selectedRegion = "West-Norway"
                        expanded = false
                    }
                )
            }
        }

        mainViewModel.setElectricityPricesToday(selectedRegion = selectedRegion)
        ShowElectricityGraph(mainUiState.electricityPricesList)

        // Calls on a function from HomeScreen.kt that lets user navigate to different screens
        NavigateScreensComposable(
            onNavigateToHomeScreen = onNavigateToHomeScreen,
            onNavigateToForecastScreen = onNavigateToForecastScreen,
            onNavigateToElectricityScreen = onNavigateToElectricityScreen
        )
    }
}

@Composable
fun ShowElectricityGraph(list: List<Double>) {
    val chartEntryModelProducer =
        entryModelOf(List(list.size) { FloatEntry(it.toFloat(), list[it].toFloat()) })

    Chart(
        chart = lineChart(),
        model = chartEntryModelProducer,
        startAxis = startAxis(),
        bottomAxis = bottomAxis(),
    )
}