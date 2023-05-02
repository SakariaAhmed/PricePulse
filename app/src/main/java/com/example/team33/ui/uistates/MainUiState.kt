package com.example.team33.ui.uistates

import com.example.team33.network.LocationForecast
import com.example.team33.network.ElectricityRegion
import com.example.team33.network.StroemprisData

data class MainUiState(
    var forecast: LocationForecast? = null,
    var electricityPrices: List<Double>? = null,
    var currentRegion: ElectricityRegion = ElectricityRegion.NO1,
)