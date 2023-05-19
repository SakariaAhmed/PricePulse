package com.example.team33.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.team33.R
import com.example.team33.ui.chart.EmptyAppChartCard
import com.example.team33.ui.chart.PopulatedChartCard
import com.example.team33.ui.genSineWaveList
import com.example.team33.ui.theme.Team33Theme
import com.example.team33.ui.uistates.MainUiState
import java.util.Date
import java.util.Locale


// Returns current hour
private fun getCurrentHour(): Int {
    return SimpleDateFormat("HH", Locale.getDefault()).format(Date()).toInt()
}

@Composable
fun HomeScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
    Column(
        // horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxSize(1f)
    ) {
        SpotpriceComposable(currentPrice = mainUiState.electricityPrices?.get(getCurrentHour()))
        ShowElectricityGraph(
            priceList = mainUiState.electricityPrices, thresholdValue = mainUiState.maxPrice
        )
    }
}


// Displays location, date and current electricity price
@Composable
private fun SpotpriceComposable(currentPrice: Double?, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier

    ) {
        Text(
            stringResource(id = R.string.spot_price),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 10.dp)
        )

        val spotPrice = when (currentPrice) {
            null -> stringResource(id = R.string.wait)
            else -> "%.2f NOK/kWh".format(currentPrice)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 30.dp),
                modifier = Modifier.size(width = 280.dp, height = 80.dp)

            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        spotPrice,
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun ShowElectricityGraph(
    modifier: Modifier = Modifier,
    priceList: List<Double>? = null,
    thresholdValue: Float? = null,
) {
    when (priceList) {
        null -> EmptyAppChartCard(24)
        else -> PopulatedChartCard(
            list = priceList,
            thresholdValue = thresholdValue,
            modifier = modifier
        )
    }
}

@Preview(showBackground = true, name = "Light Mode")
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES, name = "Dark Mode")
@Composable
private fun HomeScreenPreview() {
    Team33Theme(dynamicColor = false) {
        Surface {
            HomeScreen(mainUiState = MainUiState(electricityPrices = genSineWaveList(24)))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SpotpricePreview() {
    Team33Theme(dynamicColor = false) {
        Surface {
            SpotpriceComposable(null)
        }
    }
}

@Preview(showBackground = true, name = "Light")
@Preview(showBackground = true, name = "Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ElectricityGraphPreview() {
    Team33Theme(dynamicColor = false) {
        Surface {
            ShowElectricityGraph(
                priceList = genSineWaveList(24), thresholdValue = 1.0f
            )
        }
    }
}