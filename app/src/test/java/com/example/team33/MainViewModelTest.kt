package com.example.team33

import com.example.team33.network.LocationForecastApi
import com.example.team33.ui.viewmodel.MainViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MainViewModelTest {

    val view = MainViewModel()


    @Test
    fun stroem_isNotNull() {

        // Testing if StroemprisApi return an Array with elements in it
        val data = view.uiState.value.electricityPrices
        Assert.assertNotNull(data)
    }

    @Test
    fun forecast_isNotNull() = runBlocking {

        // Testing if LocationForecastApi contains data

        val data = LocationForecastApi.getLocationForecast()
        Assert.assertNotNull(data)
        Assert.assertNotNull(data.properties)
        Assert.assertNotNull(data.properties.meta)
        Assert.assertNotNull(data.properties.meta.units)
        Assert.assertNotNull(data.properties.meta.units.air_temperature)
        Assert.assertNotNull(data.properties.meta.units.precipitation_amount)
        Assert.assertNotNull(data.properties.meta.units.wind_speed)
        Assert.assertNotNull(data.properties.meta.units.relative_humidity)
        Assert.assertNotNull(data.properties.meta.updated_at)
        Assert.assertNotNull(data.properties.timeseries)
    }
}