package com.example.team33

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.team33.ui.screens.AppliancesScreen
import com.example.team33.ui.viewmodel.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AppliancesScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    val viewModel = MainViewModel()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            AppliancesScreen(viewModel = viewModel)
        }

    }

    // Test`s if title is displayed
    @Test
    fun screenTitle_isDisplayed() {
        composeTestRule.onNodeWithText("Appliances Screen").assertIsDisplayed()
    }
}