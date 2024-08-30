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

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

// https://developer.android.com/develop/ui/compose/libraries#viewmodel
import androidx.lifecycle.viewmodel.compose.viewModel;

@Composable
fun Home(
        modifier: Modifier = Modifier,
        viewModel: CustomViewModel = viewModel()) {

    // Consuming a Flow safely from the ViewModel
    // https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects#3
    val suggestedDestinations by viewModel.suggestedDestinations.collectAsStateWithLifecycle()
    suggestedDestinations.forEach { Log.i(Constants.TAG, "suggestedDestination includes ${it}") }

    var currentScreen: Destination by remember { mutableStateOf(Fly) }
    val onPeopleChanged: (Int) -> Unit = { viewModel.updatePeople(it) }
    val onToDestinationChanged: (String) -> Unit = { viewModel.toDestinationChanged(it) }
    Scaffold(
        topBar = {
            CustomTabBar(modifier) { tabBarModifier ->
                CustomTabs(
                    modifier = tabBarModifier,
                    allScreens = customTabRowScreens,
                    onTabSelected = { screen ->
                        if (currentScreen != screen ) {
                            currentScreen = screen
                            // Reset the count of people when switching tabs
                            onPeopleChanged(1)
                        }
                    },
                    currentScreen = currentScreen
                )
            }
        }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            currentScreen.screen(
                onPeopleChanged = onPeopleChanged,
                onToDestinationChanged = onToDestinationChanged)
        }
    }
}