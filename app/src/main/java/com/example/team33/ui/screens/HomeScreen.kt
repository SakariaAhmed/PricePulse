package com.example.team33.ui.screens

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.team33.R
import com.example.team33.ui.uistates.MainUiState
import com.example.team33.ui.viewmodels.MainViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToElectricityScreen: () -> Unit,
    onNavigateToAppliancesScreen: () -> Unit,
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

        //name of the screen
        Text(text = "Home Screen", fontSize = 40.sp)

        val time = SimpleDateFormat("yyyy/MM-dd-hh-mm-ss", Locale.getDefault()).format(Date())
        val hour = time[11].toString() + time[12].toString()
        if (hour[0] == '0') {
            hour.drop(1)
        }
        mainViewModel.setCurrentElectricityPrice(hour.toInt())

        Text(text = stringResource(id = R.string.spot_price))
        Text(text = "${mainUiState.currentElectricityPrice} NOK_per_kWh")

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

        // Calls on a function that lets user navigate to different screens
        NavigateScreensComposable(
            onNavigateToHomeScreen = onNavigateToHomeScreen,
            onNavigateToElectricityScreen = onNavigateToElectricityScreen,
            onNavigateToAppliancesScreen = onNavigateToAppliancesScreen,
        )
    }
}

// Lets user navigate to different screens
@Composable
fun NavigateScreensComposable(
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToElectricityScreen: () -> Unit,
    onNavigateToAppliancesScreen: () -> Unit,
) {
    // A row of buttons to multiple screens
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {

        // Button to home screen
        Button(
            onClick = onNavigateToHomeScreen,
            modifier = Modifier.size(width = 131.dp, height = 75.dp),
            shape = RectangleShape
        ) {
            //name of the button
            Image(
                painter = painterResource(R.drawable.home),
                contentDescription = "HomeScreen",
                modifier= Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()

            )
        }

        // Button to electricity screen
        Button(
            onClick = onNavigateToElectricityScreen,
            modifier = Modifier.size(width = 131.dp, height = 75.dp),
            shape = RectangleShape
        ) {
            //name of the button
            Image(
                painter = painterResource(R.drawable.forecast),
                contentDescription = "ElectricityScreen",
                modifier= Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }

        // Button to appliances screen
        Button(
            onClick = onNavigateToAppliancesScreen,
            modifier = Modifier.size(width = 131.dp, height = 75.dp),
            shape = RectangleShape
        ) {
            //name of the button
            Image(
                painter = painterResource(R.drawable.appliance),
                contentDescription = "ApplianceScreen",
                modifier= Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()

            )
        }
    }
}