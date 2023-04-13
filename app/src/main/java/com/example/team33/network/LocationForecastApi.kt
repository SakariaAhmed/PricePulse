package com.example.team33.network


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

private const val TAG = "LocationforecastApi"


data class Coordinates(val lat: Double, val lon: Double)


object LocationForecastApi {
    private val jsonClient by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json{
                    ignoreUnknownKeys = true
                })
            }
            expectSuccess=true
        }
    }

    private const val BASE_URL = "https://gw-uio.intark.uh-it.no/in2000/weatherapi/locationforecast/2.0/compact"

    suspend fun getLocationForecast(): LocationForecast {
        // NOTE: This should be fetched and calculated from users position
        val bergen = Coordinates(60.3925, 5.323333)
        val token = "${BASE_URL}?lat=${bergen.lat}&lon=${bergen.lon}"
        return jsonClient.get(token){
            headers{
                append("X-Gravitee-API-Key", "9118b688-6e0e-43a5-80dd-d9d6cc2db8b2")
            }
        }.body()
    }
}