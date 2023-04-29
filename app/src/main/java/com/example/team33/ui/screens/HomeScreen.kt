package com.example.team33.ui.screens

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.team33.R
import com.example.team33.ui.uistates.MainUiState
import com.example.team33.ui.viewmodels.MainViewModel
import java.util.*

@Composable
fun HomeScreen(
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
    )
    {
        // TODO: Implement adaptive layout
        if (windowSize == WindowWidthSizeClass.Compact) {

        }
        if (windowSize == WindowWidthSizeClass.Medium) {

        }
        if (windowSize == WindowWidthSizeClass.Expanded) {

        }

        //name of the screen
        Text(text = "Home Screen", fontSize = 40.sp)

        // Calls on a private function that displays location, date and current electricity price
        InformationComposable(mainViewModel, mainUiState)

        Column(modifier = Modifier.weight(3f)) {

        }

        // Calls on a function that lets user navigate to different screens
        NavigateScreensComposable(
            onNavigateToHomeScreen = onNavigateToHomeScreen,
            onNavigateToForecastScreen = onNavigateToForecastScreen,
            onNavigateToElectricityScreen = onNavigateToElectricityScreen
        )

    }
}

// Displays location, date and current electricity price
@Composable
private fun InformationComposable(mainViewModel: MainViewModel, mainUiState: MainUiState) {
    Spacer(modifier = Modifier.padding(15.dp))
    Column {
        val time = SimpleDateFormat("yyyy/MM-dd-hh-mm-ss", Locale.getDefault()).format(Date())
        val year = time[0].toString() + time[1].toString() + time[2].toString() + time[3].toString()
        val month = time[5].toString() + time[6].toString()
        val day = time[8].toString() + time[9].toString()
        val hour = time[11].toString() + time[12].toString()
        if (hour[0] == '0') {
            hour.drop(1)
        }
        val minute = time[14].toString() + time[15].toString()
        val second = time[17].toString() + time[18].toString()

        mainViewModel.setCurrentElectricityPrice(hour.toInt())
        Text(text = stringResource(id = R.string.oslo))
        Text(text = "$hour:$minute:$second")
        Text(text = "$day/$month/$year")
        Text(text = "${mainUiState.currentElectricityPrice} NOK_per_kWh")
    }
}

// Lets user navigate to different screens
@Composable
fun NavigateScreensComposable(
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToForecastScreen: () -> Unit,
    onNavigateToElectricityScreen: () -> Unit
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
                modifier=Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()

            )
        }

        // Button to forecast screen
        Button(
            onClick = onNavigateToForecastScreen,
            modifier = Modifier.size(width = 131.dp, height = 75.dp),
            shape = RectangleShape
        ) {
            //name of the button
            Image(
                painter = painterResource(R.drawable.appliance),
                contentDescription = "ApplianceScreen",
                modifier=Modifier
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
                contentDescription = "ForecastScreen",
                modifier=Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
    }
}