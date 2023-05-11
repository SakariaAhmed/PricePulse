package com.example.team33

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.team33.ui.screens.ElectricityScreen
import com.example.team33.ui.screens.HomeScreen
import com.example.team33.ui.viewmodels.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ElectricityScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    val viewModel = MainViewModel()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ElectricityScreen(viewModel = viewModel)
        }

    }

    // Test`s if title is displayed
    @Test
    fun screenTitle_isDisplayed() {
        composeTestRule.onNodeWithText("Electricity Screen").assertIsDisplayed()
    }

}