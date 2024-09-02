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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devwindsw.composenavigationandstate.details.Place
import com.devwindsw.composenavigationandstate.di.DefaultDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CustomViewModel @Inject constructor(
    private val dataRepository: CustomDataRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    val hotels: List<Place> = dataRepository.hotels
    val restaurants: List<Place> = dataRepository.restaurants

    // UI state production pipeline
    // https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects#2
    // https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
    // Backing property to avoid state updates from other classes
    private val _suggestedDestinations = MutableStateFlow<List<Place>>(emptyList())
    // The UI collects from this StateFlow to get its state updates
    val suggestedDestinations: StateFlow<List<Place>> = _suggestedDestinations.asStateFlow()

    init {
        _suggestedDestinations.value = dataRepository.destinations
    }

    fun updatePeople(people: Int) {
        Log.i(Constants.TAG, "updatePeople ${people}")
    }

    fun toDestinationChanged(newDestination: String) {
        Log.i(Constants.TAG, "toDestinationChanged ${newDestination}")
        // Use Kotlin coroutines with lifecycle-aware components
        // https://developer.android.com/topic/libraries/architecture/coroutines#viewmodelscope
        viewModelScope.launch {
            val newDestinations = withContext(defaultDispatcher) {
                dataRepository.destinations
                    .filter { it.city.nameToDisplay.contains(newDestination) }
            }
            _suggestedDestinations.value = newDestinations
        }
    }
}