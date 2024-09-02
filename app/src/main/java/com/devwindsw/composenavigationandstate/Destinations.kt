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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.devwindsw.composenavigationandstate.base.ExploreSection
import com.devwindsw.composenavigationandstate.details.Place

/**
 * Contract for information needed on every custom navigation destination
 */
interface Destination {
    val title: String
    val screen: @Composable (
        onPeopleChanged: (Int) -> Unit,
        onToDestinationChanged: (String) -> Unit
    ) -> Unit
    val search: @Composable (
        placeList: List<Place>,
        onExploreItemClicked: (Place) -> Unit
    ) -> Unit
}

/**
 * Custom navigation destinations
 */
object Fly : Destination {
    override val title = "FLY"
    override val screen: @Composable (
        onPeopleChanged: (Int) -> Unit,
        onToDestinationChanged: (String) -> Unit) -> Unit = {
            onPeopleChanged, onToDestinationChanged ->
                FlyScreen(onPeopleChanged = onPeopleChanged, onToDestinationChanged = onToDestinationChanged)
    }
    override val search: @Composable (
        placeList: List<Place>,
        onExploreItemClicked: (Place) -> Unit) -> Unit = {
        placeList, onExploreItemClicked ->
            ExploreSection(
                title = "Explore Flights by Destination",
                placeList = placeList,
                onItemClicked = onExploreItemClicked)
    }

}

object Sleep : Destination {
    override val title = "SLEEP"
    override val screen: @Composable (
        onPeopleChanged: (Int) -> Unit,
        onToDestinationChanged: (String) -> Unit) -> Unit = {
            onPeopleChanged, onToDestinationChanged ->
                SleepScreen(onPeopleChanged = onPeopleChanged)
    }
    override val search: @Composable (
        placeList: List<Place>,
        onExploreItemClicked: (Place) -> Unit) -> Unit = {
            placeList, onExploreItemClicked ->
            ExploreSection(
                title = "Explore Hotels by Destination",
                placeList = placeList,
                onItemClicked = onExploreItemClicked)
    }
}

object Eat : Destination {
    override val title = "EAT"
    override val screen: @Composable (
        onPeopleChanged: (Int) -> Unit,
        onToDestinationChanged: (String) -> Unit) -> Unit = {
            onPeopleChanged, onToDestinationChanged ->
                EatScreen(onPeopleChanged = onPeopleChanged)
    }
    override val search: @Composable (
        placeList: List<Place>,
        onExploreItemClicked: (Place) -> Unit) -> Unit = {
            placeList, onExploreItemClicked ->
            ExploreSection(
                title = "Explore Hotels by Destination",
                placeList = placeList,
                onItemClicked = onExploreItemClicked)
    }
}

// Screens to be displayed in the top CustomTabRow
val customTabRowScreens = listOf(Fly, Sleep, Eat)