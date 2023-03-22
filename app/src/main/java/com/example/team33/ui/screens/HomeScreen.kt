package com.example.team33.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeApp() {
    val mainViewModel: MainViewModel = viewModel()
    val mainUiState by mainViewModel.uiState.collectAsState()

    HomeScreen(mainViewModel, mainUiState)
}

@Composable
fun HomeScreen(mainViewModel: MainViewModel, mainUiState: MainUiState) {
    Text(text = "text")
}
