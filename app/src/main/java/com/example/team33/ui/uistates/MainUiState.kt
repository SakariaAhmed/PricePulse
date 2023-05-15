package com.example.team33.ui.uistates

import com.example.team33.network.ElectricityRegion
import com.example.team33.network.LocationForecast

data class MainUiState(
    var forecast: LocationForecast? = null,
    var electricityPrices: List<Double>? = null,
    var currentRegion: ElectricityRegion = ElectricityRegion.NO1,
    var currentHour: Int = 0,
    var maxPrice: Float = 0.75f
)