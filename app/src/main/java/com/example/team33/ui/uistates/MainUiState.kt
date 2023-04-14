package com.example.team33.ui.uistates

import com.example.team33.network.LocationForecast
import com.example.team33.network.StroemprisData

data class MainUiState(
    var stroemList: List<StroemprisData>,
    var forecast: LocationForecast? = null,
    var currentElectricityPrice: Double = 0.0,
    var electricityPricesList: List<Double> = listOf(), // Stores the electricity prices for the last 24 hours
    var selectedRegion: String = ""
)