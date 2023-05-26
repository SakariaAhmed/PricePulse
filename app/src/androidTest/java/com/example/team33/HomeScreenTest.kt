package com.example.team33

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.team33.ui.screens.HomeScreen
import com.example.team33.ui.viewmodel.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    val viewModel = MainViewModel()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HomeScreen(viewModel = viewModel)
        }
    }

    // Test`s if title is displayed
    @Test
    fun screenTitle_isDisplayed() {
        composeTestRule.onNodeWithText("Home Screen").assertIsDisplayed()
    }

    // Test`s if title is displayed
    @Test
    fun exposedDropdownMenu_click() {
        composeTestRule.onNodeWithText("Select region").assertIsDisplayed()
        composeTestRule.onNodeWithText("Select region").performClick()
    }


    // Test`s if title is displayed
    @Test
    fun menuItemEast_isDisplayed() {
        composeTestRule.onNodeWithText("East-Norway").assertIsDisplayed()
    }

    @Test
    fun menuItemSouth_isDisplayed() {
        composeTestRule.onNodeWithText( R.string.south_norway.toString()).assertIsDisplayed()
    }

    @Test
    fun menuItemMid_isDisplayed() { 
        composeTestRule.onNodeWithText("Mid-Norway").assertIsDisplayed()
    }

    @Test
    fun menuItemNorth_isDisplayed() {
        composeTestRule.onNodeWithText("North-Norway").assertIsDisplayed()
    }

    @Test
    fun menuItemWest_isDisplayed() {
        composeTestRule.onNodeWithText("West-Norway").assertIsDisplayed()
    }

}