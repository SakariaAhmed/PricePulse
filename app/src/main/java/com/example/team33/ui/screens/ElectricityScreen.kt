package com.example.team33.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.team33.R
import com.example.team33.ui.viewmodels.MainViewModel
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf

// Displays the electricity price of chosen region for the current day
@Composable
fun ElectricityScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val mainUiState by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(text = "Electricity Screen", fontSize = 40.sp)

        val regionOptions: List<String> = listOf(
            stringResource(id = R.string.east_norway),
            stringResource(id = R.string.south_norway),
            stringResource(id = R.string.mid_norway),
            stringResource(id = R.string.north_norway),
            stringResource(id = R.string.west_norway)
        )
        Text(text = stringResource(id = R.string.selected_region) + ": ${regionOptions[mainUiState.currentRegion.ordinal]}")

        // FIXME: Don't show a graph if there was an error fetching data from API
        if (mainUiState.electricityPrices != null) {
            ShowElectricityGraph(mainUiState.electricityPrices!!)
        }
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