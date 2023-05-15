package com.example.team33.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.team33.ui.chart.PopulatedChartCard
import com.example.team33.ui.genSineWaveList
import com.example.team33.ui.theme.Team33Theme
import com.example.team33.ui.uistates.MainUiState

// Displays the electricity price of chosen region for the current day
@Composable
fun ElectricityScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
    ElectricityScreenUI(mainUiState = mainUiState, modifier = modifier)
}


@Composable
fun ElectricityScreenUI(mainUiState: MainUiState, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxSize()
    ) {
        // TODO: Use forecasted data
        PopulatedChartCard(list = genSineWaveList(6 * 24), thresholdValue = mainUiState.maxPrice)
    }
}


@Preview
@Composable
private fun ShowElectricityGraphPreview(){
    Team33Theme(dynamicColor = false) {
        Surface {
            ElectricityScreenUI(MainUiState())
        }
    }
}