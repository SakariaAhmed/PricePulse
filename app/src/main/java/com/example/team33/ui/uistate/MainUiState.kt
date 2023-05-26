package com.example.team33.ui.uistate

import com.example.team33.network.ElectricityRegion

data class MainUiState(
    var electricityPrices: List<Double>? = null,
    var currentRegion: ElectricityRegion = ElectricityRegion.NO1,
    var currentHour: Int = 0,
    var maxPrice: Float = 1.28f,
    var showGraph: Boolean = true,
    var appliance: String = "Washing"
)