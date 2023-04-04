package com.example.team33.ui.screens

import com.example.team33.network.LocationForecast
import com.example.team33.network.StroemprisData

data class MainUiState(
    var stroemList:List<StroemprisData>,
    var forecast: LocationForecast? =null,
    var currentElectricityPrice: Double = 0.0

)