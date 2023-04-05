package com.example.team33.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team33.network.*
import com.example.team33.ui.uistates.MainUiState
import io.ktor.client.plugins.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState(emptyList()))
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        getPrice()
        getLocationForcast()
    }

    // TODO: Extracting the electricity price from the data
    fun getPrice() {
        viewModelScope.launch(Dispatchers.IO) {
            var data = emptyList<StroemprisData>()
            try {
                data = StroemprisApi.getCurrentPriceFromRegion(StroemprisApiRegion.NO1)
            } catch (e: RedirectResponseException) {
                val errorMsg = "${e.response.status}: ${e.response.call.request.url}"
                Log.d(TAG, errorMsg)
            } catch (e: ClientRequestException) {
                val errorMsg = "${e.response.status}: ${e.response.call.request.url}"
                Log.e(TAG, errorMsg)
            } catch (e: ServerResponseException) {
                val errorMsg = "${e.response.status}: ${e.response.call.request.url}"
                Log.e(TAG, errorMsg)
            } catch (e: Exception) {
                val errorMsg = "Something terrible went wrong because:"
                Log.e(TAG, "$errorMsg $e")
            }
            _uiState.update { currentState ->
                currentState.copy(
                    stroemList = data
                )

            }
        }
    }

    fun getLocationForcast() {
        viewModelScope.launch(Dispatchers.IO) {
            var data: LocationForecast? = null
            try {
                data = LocationForecastApi.getLocationForecast()
            } catch (e: RedirectResponseException) {
                val errorMsg = "${e.response.status}: ${e.response.call.request.url}"
                Log.d(TAG, errorMsg)
            } catch (e: ClientRequestException) {
                val errorMsg = "${e.response.status}: ${e.response.call.request.url}"
                Log.e(TAG, errorMsg)
            } catch (e: ServerResponseException) {
                val errorMsg = "${e.response.status}: ${e.response.call.request.url}"
                Log.e(TAG, errorMsg)
            } catch (e: Exception) {
                val errorMsg = "Something terrible went wrong because:"
                Log.e(TAG, "$errorMsg $e")
            }
            _uiState.update { currentState ->
                currentState.copy(
                    forecast = data
                )

            }
        }
    }

    fun setCurrentElectricityPrice(hour: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = StroemprisApi.getCurrentPriceFromRegion(StroemprisApiRegion.NO1)

            _uiState.value.currentElectricityPrice = data[hour].NOK_per_kWh
        }
    }
}