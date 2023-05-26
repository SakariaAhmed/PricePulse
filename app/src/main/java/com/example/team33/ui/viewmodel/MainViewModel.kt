package com.example.team33.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team33.network.ElectricityRegion
import com.example.team33.network.StroemprisApi
import com.example.team33.network.StroemprisData
import com.example.team33.ui.uistate.MainUiState
import io.ktor.client.plugins.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        getElectricityPrice()
    }

    fun changeElectricityRegion(region: ElectricityRegion) {
        if (_uiState.value.currentRegion != region) {
            _uiState.update { currentState ->
                currentState.copy(currentRegion = region)
            }
            getElectricityPrice()
        }
    }

    // Fetches electricity prices from `strømpris API`
    private fun getElectricityPrice() {
        viewModelScope.launch(Dispatchers.IO) {
            var data = emptyList<StroemprisData>()
            try {
                data = StroemprisApi.getCurrentPriceFromRegion(_uiState.value.currentRegion)
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
                    // remove all other unnecessary properties for fetched data
                    electricityPrices = data.map { it.NOK_per_kWh }
                )
            }
        }
    }

    fun graphVisible(statement: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(
                // remove all other unnecessary properties for fetched data
                showGraph = statement
            )
        }
    }

    fun changeAppliance(chosenAppliance: String) {
        _uiState.update { currentState ->
            currentState.copy(
                // remove all other unnecessary properties for fetched data
                appliance = chosenAppliance
            )
        }
    }
}