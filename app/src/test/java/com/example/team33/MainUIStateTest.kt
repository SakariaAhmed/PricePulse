package com.example.team33

import com.example.team33.network.ElectricityRegion
import com.example.team33.ui.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Test

class MainUIStateTest {

    val viewModel = MainViewModel()

    @Test
    fun stroem_isNull() {
        val data = viewModel.uiState.value.electricityPrices
        Assert.assertNull(data)
    }

    @Test
    fun currentRegion_Test() {
        Assert.assertNotNull(viewModel.uiState.value.currentRegion)
        assert(viewModel.uiState.value.currentRegion == ElectricityRegion.NO1)
    }
}