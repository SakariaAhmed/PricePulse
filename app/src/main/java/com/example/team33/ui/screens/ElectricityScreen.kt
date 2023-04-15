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

        var selectedOptionText by remember { mutableStateOf(mainUiState.selectedRegion) }
        var expanded by remember { mutableStateOf(false) }
        val regionOptions: List<String> = listOf(
            stringResource(id = R.string.east_norway),
            stringResource(id = R.string.south_norway),
            stringResource(id = R.string.mid_norway),
            stringResource(id = R.string.north_norway),
            stringResource(id = R.string.west_norway)
        )

        // Dropdown-Menu that lets user select the electricity price from 5 different regions in Norway
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                label = { Text(stringResource(R.string.select_region)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                regionOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            mainUiState.selectedRegion = selectionOption
                            expanded = false
                        }
                    )
                }
            }
        }

        mainViewModel.setElectricityPricesToday(selectedRegion = mainUiState.selectedRegion)
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
    val chartEntryModelProducer = entryModelOf(List(list.size) { FloatEntry(it.toFloat(), list[it].toFloat()) })

    Chart(
        chart = lineChart(),
        model = chartEntryModelProducer,
        startAxis = startAxis(),
        bottomAxis = bottomAxis(),
    )
}