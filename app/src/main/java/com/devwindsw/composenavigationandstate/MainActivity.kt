package com.devwindsw.composenavigationandstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.devwindsw.composenavigationandstate.details.Place
import com.devwindsw.composenavigationandstate.details.launchDetailsActivity
import com.devwindsw.composenavigationandstate.ui.theme.ComposeNavigationAndStateTheme
import dagger.hilt.android.AndroidEntryPoint

// Dependency injection with Hilt
// https://developer.android.com/training/dependency-injection/hilt-android
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeNavigationAndStateTheme {
                MainScreen(onExploreItemClicked = {
                    launchDetailsActivity(
                        context = this,
                        item = it
                    )
                })
            }
        }
    }
}

@Composable
private fun MainScreen(onExploreItemClicked: (Place) -> Unit) {
    Surface(color = MaterialTheme.colorScheme.primary) {
        var showLandingScreen by remember {
            mutableStateOf(true)
        }
        if (showLandingScreen) {
            LandingScreen(onTimeout = { showLandingScreen = false })
        } else {
            Home(onExploreItemClicked = onExploreItemClicked)
        }
    }
}