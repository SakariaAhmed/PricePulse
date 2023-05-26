package com.example.team33.network

import android.icu.text.SimpleDateFormat
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.util.*

enum class ElectricityRegion {
    NO1, // Oslo / Øst-Norge
    NO2, // Kristiansand / Sør-Norge
    NO3, // Trondheim / Midt-Norge
    NO4, // Tromsø / Nord-Norge
    NO5, // Bergen / Vest-Norge
}

object StroemprisApi {
    private val jsonClient by lazy {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
            expectSuccess = true
        }
    }

    private const val BASE_URL = "https://www.hvakosterstrommen.no/api/v1/prices"

    suspend fun getCurrentPriceFromRegion(region: ElectricityRegion): List<StroemprisData> {
        // get current date (year, month, day)
        val today = SimpleDateFormat("yyyy/MM-dd", Locale.getDefault()).format(Date())

        val path = "${BASE_URL}/${today}_${region.name}.json"
        return jsonClient.get(path).body()
    }
}