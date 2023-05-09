package com.example.team33.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.team33.R
import com.example.team33.network.ElectricityRegion
import com.example.team33.ui.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(viewModel: MainViewModel) {
    val mainUiState by viewModel.uiState.collectAsState()

    var expanded by remember { mutableStateOf(false) }
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

        Text(text = stringResource(id = R.string.language))
        Text(text = stringResource(id = R.string.change_language))
    }
}