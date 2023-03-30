package com.example.team33.ui.screens

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.team33.R
import java.util.*

@Composable
fun HomeApp(modifier: Modifier, onNavigateToNext: () -> Unit ) {
    val mainViewModel: MainViewModel = viewModel()
    val mainUiState by mainViewModel.uiState.collectAsState()


    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        //name of the screen
        Text(text = "Home Screen")

        informationComposable(mainViewModel, mainUiState)

        Column(modifier = Modifier.weight(3f)) {

        }

        //button to the next screen
        Button(
            onClick = onNavigateToNext,
            Modifier
                .height(75.dp)
                .fillMaxWidth(), shape = RectangleShape
        ) {
            //name of the button
            Text(text = "Prognose")
        }
    }
}

@Composable
fun HomeScreen(mainViewModel: MainViewModel, mainUiState: MainUiState) {

}

// Displays location, date and current electricity price
@Composable
fun informationComposable(mainViewModel: MainViewModel, mainUiState: MainUiState) {
    Spacer(modifier = Modifier.padding(15.dp))
    Column() {
        val time = SimpleDateFormat("yyyy/MM-dd-hh-mm-ss", Locale.getDefault()).format(Date())
        val year = time[0].toString() + time[1].toString() + time[2].toString() + time[3].toString()
        val month = time[5].toString() + time[6].toString()
        val day = time[8].toString() + time[9].toString()
        val hour = time[11].toString() + time[12].toString()
        if (hour[0] == '0') { hour.drop(1) }
        val minute = time[14].toString() + time[15].toString()
        val second = time[17].toString() + time[18].toString()

        mainViewModel.setCurrentElectricityPrice(hour.toInt())
        Text(text = stringResource(id = R.string.oslo))
        Text(text = "$hour:$minute:$second")
        Text(text = "$day/$month/$year")
        Text(text = "${mainUiState.currentElectricityPrice} NOK_per_kWh")
    }
}