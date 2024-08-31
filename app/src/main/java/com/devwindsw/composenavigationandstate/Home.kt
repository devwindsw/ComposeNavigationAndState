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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

// https://developer.android.com/develop/ui/compose/libraries#viewmodel
import androidx.lifecycle.viewmodel.compose.viewModel;
import kotlinx.coroutines.launch

@Composable
fun Home(
        modifier: Modifier = Modifier,
        viewModel: CustomViewModel = viewModel()) {

    // Consuming a Flow safely from the ViewModel
    // https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects#3
    val suggestedDestinations by viewModel.suggestedDestinations.collectAsStateWithLifecycle()
    if (suggestedDestinations.isEmpty()) {
        Log.i(
            Constants.TAG,
            "suggestedDestination is empty"
        )
    } else {
        suggestedDestinations.forEach {
            Log.i(
                Constants.TAG,
                "suggestedDestination includes ${it}"
            )
        }
    }

    var currentScreen: Destination by remember { mutableStateOf(Fly) }
    val onPeopleChanged: (Int) -> Unit = { viewModel.updatePeople(it) }
    val onToDestinationChanged: (String) -> Unit = { viewModel.toDestinationChanged(it) }

    // Navigation drawer
    // https://developer.android.com/develop/ui/compose/components/drawer
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    // rememberCoroutineScope
    // https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects#5
    /* Using the rememberCoroutineScope API returns a CoroutineScope bound to the point
     * in the Composition where you call it.
     * The scope will be automatically canceled once it leaves the Composition.
     * With that scope, you can start coroutines when you're not in the Composition
     */
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CustomDrawer()
        }
    ) {
        Scaffold(
            topBar = {
                CustomTabBar(
                    modifier = modifier,
                    onMenuClicked = {
                        /* https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects#5
                         * drawerState.open function is a suspend function.
                         * open() must be called within a coroutine.
                         * What can you do? onMenuClicked is a simple callback function, therefore:
                         * i) You cannot simply call suspend functions in it
                         *    because onMenuClicked is not executed in the context of a coroutine.
                         * ii) You cannot use LaunchedEffect because we can't call composables in onMenuClicked,
                         *     since we're not in the Composition.
                         *
                         * You want to launch a coroutine; which scope should we use?
                         * Ideally, you'd want a CoroutineScope that follows the lifecycle of its call-site.
                         */
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) { tabBarModifier ->
                    CustomTabs(
                        modifier = tabBarModifier,
                        allScreens = customTabRowScreens,
                        onTabSelected = { screen ->
                            if (currentScreen != screen) {
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
                    onToDestinationChanged = onToDestinationChanged
                )
            }
        }
    }
}