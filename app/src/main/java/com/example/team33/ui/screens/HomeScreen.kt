package com.example.team33.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
