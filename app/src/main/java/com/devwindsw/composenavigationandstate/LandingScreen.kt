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

package com.devwindsw.composenavigationandstate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(onTimeout: () -> Unit, modifier: Modifier = Modifier) {
    /*
     * Start a side effect to load things in the background
     * and call onTimeout() when finished.
     * If onTimeout changes while the side-effect is in progress,
     * there's no guarantee that the last onTimeout is called when the effect finishes.
     * To guarantee that the last onTimeout is called,
     * remember onTimeout using the rememberUpdatedState API.
     * This API captures and updates the newest value:
     */
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        // Adds composition consistency. Use the value when LaunchedEffect is first called.
        /* This will always refer to the latest onTimeout function that
         * LandingScreen was recomposed with.
         * You should use rememberUpdatedState when a long-lived lambda
         * or object expression references parameters
         * or values computed during composition,
         * which might be common when working with LaunchedEffect. */
        val currentOnTimeout by rememberUpdatedState(onTimeout)

        /* Create an effect that matches the lifecycle of LandingScreen.
         * LaunchedEffect take a variable number of keys as a parameter
         * that are used to restart the effect whenever one of those keys changes.
         * If LandingScreen recomposes or onTimeout changes,
         * the delay shouldn't start again.
         * To trigger the side-effect only once during the lifecycle of this composable,
         * use a constant as a key. */
        LaunchedEffect(Unit) {
            // simulate loading things in the background.
            delay(SplashWaitTime)
            currentOnTimeout()
        }
        Image(painterResource(id = R.drawable.ic_crane_drawer), contentDescription = null)
    }
}