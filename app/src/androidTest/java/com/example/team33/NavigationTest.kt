package com.example.team33

import androidx.compose.material3.windowsizeclass.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.team33.ui.viewmodels.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    val viewmodel = MainViewModel()
    val scrensize = WindowSizeClass.calculateFromSize(DpSize(600.dp,600.dp))




    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            AppScreen(scrensize, viewmodel)
        }
    }

    @Test
    fun bottomNavigation_verifyStartDestination() {
        composeTestRule.onNodeWithContentDescription("Home").assertIsDisplayed()

    }

    @Test
    fun bottomNavigation_clickElectricity() {
        composeTestRule.onNode(hasContentDescription("Electricity")).performClick()
        composeTestRule.onNodeWithContentDescription("Electricity").assertIsDisplayed()
    }

    @Test
    fun bottomNavigation_clickAppliances() {
        composeTestRule.onNode(hasContentDescription("Appliances")).performClick()
        composeTestRule.onNodeWithContentDescription("Appliances").assertIsDisplayed()
    }

    @Test
    fun bottomNavigation_backToHome() {
        composeTestRule.onNode(hasContentDescription("Home")).performClick()
        composeTestRule.onNodeWithContentDescription("Home").assertIsDisplayed()
    }


}



