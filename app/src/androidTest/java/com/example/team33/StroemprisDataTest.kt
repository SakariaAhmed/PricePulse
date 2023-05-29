package com.example.team33

import com.example.team33.network.ElectricityRegion
import com.example.team33.network.StroemprisApi
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Test

class StroemprisDataTest {
    @Test
    fun stroemDataStart_Test() {
        runBlocking {
            val data = StroemprisApi.getCurrentPriceFromRegion(ElectricityRegion.NO1)
            assertNotNull(data)
        }
    }
}