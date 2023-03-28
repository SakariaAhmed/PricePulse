package com.example.team33.network


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

private const val TAG = "LocationforecastApi"


data class Coordinates(val lat: Double, val lon: Double)


object LocationForecastApi {
    private val jsonClient by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
            expectSuccess=true
        }
    }

    private const val BASE_URL = "https://api.met.no/weatherapi/locationforecast/2.0/compact"

    // FIXME: There is something wrong with serialization of LocationForecast, returning with String works
    suspend fun getLocationForecast(): LocationForecast {
        // NOTE: This should be fetched and calculated from users position
        val bergen = Coordinates(60.3925, 5.323333)
        val token = "${BASE_URL}?lat=${bergen.lat}&lon=${bergen.lon}"
        return jsonClient.get(token).body()
    }
}