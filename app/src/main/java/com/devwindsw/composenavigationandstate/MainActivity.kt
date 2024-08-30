package com.devwindsw.composenavigationandstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {
    Surface(color = MaterialTheme.colorScheme.primary) {
        Home()
    }
}