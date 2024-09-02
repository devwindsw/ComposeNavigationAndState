/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.devwindsw.composenavigationandstate.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devwindsw.composenavigationandstate.ui.theme.ComposeNavigationAndStateTheme
import dagger.hilt.android.AndroidEntryPoint

// From https://github.com/android/codelab-android-compose/blob/main/AdvancedStateAndSideEffectsCodelab/app/src/main/java/androidx/compose/samples/crane/details/DetailsActivity.kt

internal const val KEY_ARG_DETAILS_CITY_NAME = "KEY_ARG_DETAILS_CITY_NAME"

fun launchDetailsActivity(context: Context, item: Place) {
    context.startActivity(createDetailsActivityIntent(context, item))
}

fun createDetailsActivityIntent(context: Context, item: Place): Intent {
    val intent = Intent(context, DetailsActivity::class.java)
    intent.putExtra(KEY_ARG_DETAILS_CITY_NAME, item.city.name)
    return intent
}

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ComposeNavigationAndStateTheme {
                Surface {
                    DetailsScreen(
                        onErrorLoading = { finish() },
                        modifier = Modifier.systemBarsPadding()
                    )
                }
            }
        }
    }
}

// For produceState
// https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects#8
data class DetailsUiState(
    val cityDetails: Place? = null,
    val isLoading: Boolean = false,
    val throwError: Boolean = false
)

@Composable
fun DetailsScreen(
    onErrorLoading: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel()
) {
    // From https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects#8
    /* produceState allows you to convert non-Compose state into Compose State.
     * It launches a coroutine scoped to the Composition that can push values into the returned State
     * using the value property.
     * As with LaunchedEffect, produceState also takes keys to cancel and restart the computation. */
    val uiState by produceState(initialValue = DetailsUiState(isLoading = true)) {
        // In a coroutine, this can call suspend functions or move
        // the computation to different Dispatchers
        val cityDetailsResult = viewModel.cityDetails
        value = if (cityDetailsResult.isSuccess) {
            DetailsUiState(cityDetailsResult.getOrNull())
        } else {
            DetailsUiState(throwError = true)
        }
    }

    /* depending on the uiState, you show the data,
     * show the loading screen, or report the error. */
    when {
        uiState.cityDetails != null -> {
            DetailsContent(uiState.cityDetails!!, modifier.fillMaxSize())
        }
        uiState.isLoading -> {
            Box(modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        else -> { onErrorLoading() }
    }
}

@Composable
fun DetailsContent(
    place: Place?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
        Spacer(Modifier.height(32.dp))
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = if (place == null) "Unknown" else place.city.nameToDisplay,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = if (place == null) "Unknown" else place.description,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        }
}
