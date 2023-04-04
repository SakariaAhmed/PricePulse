package com.example.team33.network

import kotlinx.serialization.Serializable

@Serializable
data class LocationForecast(
     val properties: Properties
)




@Serializable
data class Properties(
    val meta: Meta, val timeseries: List<TimeSeries>
)

@Serializable
data class Meta(
    val updated_at: String, val units: Units
)


@Serializable
data class Units(
    val air_temperature: String,
    val precipitation_amount: String,
    val relative_humidity: String,
    val wind_speed: String
)

@Serializable
data class TimeSeries(
    val time: String, val data: Data
)

@Serializable
data class Data(
    val instant: Instant,
    val next_12_hours: Next12Hours?=null,
    val next_1_hours: Next1Hours?=null,
    val next_6_hours: Next6Hours?=null
)

@Serializable
data class Instant(
    val details: Details
)

@Serializable
data class Next12Hours(
    val summary: Summary
)

@Serializable
data class Next1Hours(
    val summary: Summary, val details: PrecipitationDetails
)

@Serializable
data class Next6Hours(
    val summary: Summary, val details: PrecipitationDetails
)

@Serializable
data class Details(
    val air_temperature: Double,
    val relative_humidity: Double,
    val wind_speed: Double
)

@Serializable
data class Summary(
    val symbol_code: String
)

@Serializable
data class PrecipitationDetails(
    val precipitation_amount: Double
)