package com.example.team33.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeApp(modifier: Modifier, onNavigateToNext: () -> Unit ) {
    val mainViewModel: MainViewModel = viewModel()
    val mainUiState by mainViewModel.uiState.collectAsState()

    HomeScreen(mainViewModel, mainUiState)
    
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        //name of the screen
        Text(text = "Home Screen")
        
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
    Text(text = "text")
}
