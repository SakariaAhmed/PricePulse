package com.example.team33

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.team33.network.ConnectivityObserver
import com.example.team33.network.NetworkConnectivityObserver
import com.example.team33.ui.screens.HomeScreen
import com.example.team33.ui.screens.PrognoseScreen
import com.example.team33.ui.theme.Team33Theme

class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)
        setContent {
            Team33Theme {
                /*
                Status returns one of these strings [Available, Unavailable, Losing, Lost]
                based on the user's internet connection
                 */
                val status by connectivityObserver.observe().collectAsState(
                    initial = ConnectivityObserver.Status.Unavailable
                )

                val windowSize = calculateWindowSizeClass(this)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    when (status.toString()) {
                        "Available" -> {
                            MultipleScreenNavigator(windowSize = windowSize)
                        }
                        "Unavailable" -> {
                            UnavailableScreen(modifier = Modifier)
                        }
                        "Losing" -> {
                            LosingScreen(modifier = Modifier)
                        }
                        "Lost" -> {
                            LostScreen(modifier = Modifier)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MultipleScreenNavigator(windowSize: WindowSizeClass) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToNext = {
                    navController.navigate("prognose")
                },
                windowSize = windowSize.widthSizeClass,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable("prognose") {
            PrognoseScreen(
                onNavigateToNext = {
                    navController.navigate("home")
                },
                windowSize = windowSize.widthSizeClass,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

// Displays the text "Unable to connect to the internet" when user tries to launch the app without internet connection
@Composable
fun UnavailableScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Text(stringResource(R.string.unable_to_connect))
    }
}

// Displays the text "Losing internet connection..." when user is losing internet connection
@Composable
fun LosingScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Text(stringResource(R.string.losing_connection))
    }
}

// Displays the text "Lost internet connection" when user lost internet connection
@Composable
fun LostScreen(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize()) {
        Text(stringResource(R.string.lost_connection))
    }
}