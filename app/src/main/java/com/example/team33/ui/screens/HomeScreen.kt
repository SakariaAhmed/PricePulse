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
import com.example.team33.network.ElectricityRegion
import com.example.team33.ui.viewmodels.MainViewModel
import java.util.Date
import java.util.Locale
import kotlin.math.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    val mainUiState by viewModel.uiState.collectAsState()

    // TODO: I was working on implementing bottom bar for navigation
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        // name of the screen
        Text(text = "Home Screen", fontSize = 40.sp)
        SpotpriceComposable(mainUiState.electricityPrices)

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
                value = regionOptions[mainUiState.currentRegion.ordinal],
                onValueChange = {},
                label = { Text(stringResource(R.string.select_region)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                regionOptions.forEach { selectionOption ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        for ((index, region) in ElectricityRegion.values().withIndex()) {
                            if (regionOptions[index] == selectionOption) {
                                viewModel.changeElectricityRegion(region)
                            }
                        }
                    }, text = { Text(selectionOption) })
                }
            }
        }
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
        // TODO: Make `Wait...` string localizable
        val spotPrice = electricityPrices?.get(hour)?.round(2)?.toString() ?: "Wait..."

        Text(stringResource(id = R.string.spot_price))
        Text(text = "$spotPrice NOK/kWh")
    }
}