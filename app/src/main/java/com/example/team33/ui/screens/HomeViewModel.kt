package com.example.team33.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.team33.network.StroemprisApi
import com.example.team33.network.StroemprisApiRegion
import io.ktor.client.plugins.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val TAG = "HomeViewModel"

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        // TODO: implement a check for internet connection before trying to do api call
        getPrice()
    }

    // TODO: Extracting the electricity price from the data
    fun getPrice() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = StroemprisApi.getCurrentPriceFromRegion(StroemprisApiRegion.NO5)
                println(data)
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
        }
    }


}