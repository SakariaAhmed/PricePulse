package com.example.team33.ui.screens

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.team33.R
import com.example.team33.ui.uistates.MainUiState
import java.util.Date
import java.util.Locale
import kotlin.math.round

@Composable
fun HomeScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        // name of the screen
        Text(text = stringResource(id = R.string.home), fontSize = 40.sp)
        SpotpriceComposable(mainUiState.electricityPrices)
    }
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

// Displays location, date and current electricity price
@Composable
private fun SpotpriceComposable(electricityPrices: List<Double>?) {
    Column {
        val hour = SimpleDateFormat("HH", Locale.getDefault()).format(Date()).toInt()
        val spotPrice =
            electricityPrices?.get(hour)?.round(2)?.toString() ?: stringResource(id = R.string.wait)

        Text(stringResource(id = R.string.spot_price))
        Text(text = "$spotPrice NOK/kWh")
    }
}