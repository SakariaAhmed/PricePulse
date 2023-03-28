package com.example.team33.network

import kotlinx.serialization.Serializable

@Serializable
data class StroemprisData(
    val EUR_per_kWh: Double,
    val EXR: Double,
    val NOK_per_kWh: Double,
    val time_end: String,
    val time_start: String
)