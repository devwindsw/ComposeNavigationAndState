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

import com.devwindsw.composenavigationandstate.details.City
import com.devwindsw.composenavigationandstate.details.Place
import javax.inject.Inject
import javax.inject.Singleton

// From https://github.com/android/codelab-android-compose/blob/main/AdvancedStateAndSideEffectsCodelab/app/src/main/java/androidx/compose/samples/crane/data/Cities.kt
val MADRID = City(
    name = "Madrid",
    country = "Spain",
    latitude = "40.416775",
    longitude = "-3.703790"
)

val NAPLES = City(
    name = "Naples",
    country = "Italy",
    latitude = "40.853294",
    longitude = "14.305573"
)

val DALLAS = City(
    name = "Dallas",
    country = "US",
    latitude = "32.779167",
    longitude = "-96.808891"
)

val CORDOBA = City(
    name = "Cordoba",
    country = "Argentina",
    latitude = "-31.416668",
    longitude = "-64.183334"
)

val MALDIVAS = City(
    name = "Maldivas",
    country = "South Asia",
    latitude = "1.924992",
    longitude = "73.399658"
)

val ASPEN = City(
    name = "Aspen",
    country = "Colorado",
    latitude = "39.191097",
    longitude = "-106.817535"
)

val BALI = City(
    name = "Bali",
    country = "Indonesia",
    latitude = "-8.3405",
    longitude = "115.0920"
)

val BIGSUR = City(
    name = "Big Sur",
    country = "California",
    latitude = "36.2704",
    longitude = "-121.8081"
)

val KHUMBUVALLEY = City(
    name = "Khumbu Valley",
    country = "Nepal",
    latitude = "27.9320",
    longitude = "86.8050"
)

val ROME = City(
    name = "Rome",
    country = "Italy",
    latitude = "41.902782",
    longitude = "12.496366"
)

val GRANADA = City(
    name = "Granada",
    country = "Spain",
    latitude = "37.18817",
    longitude = "-3.60667"
)

val WASHINGTONDC = City(
    name = "Washington DC",
    country = "USA",
    latitude = "38.9072",
    longitude = "-77.0369"
)

val BARCELONA = City(
    name = "Barcelona",
    country = "Spain",
    latitude = "41.390205",
    longitude = "2.154007"
)

val CRETE = City(
    name = "Crete",
    country = "Greece",
    latitude = "35.2401",
    longitude = "24.8093"
)

val LONDON = City(
    name = "London",
    country = "United Kingdom",
    latitude = "51.509865",
    longitude = "-0.118092"
)

val PARIS = City(
    name = "Paris",
    country = "France",
    latitude = "48.864716",
    longitude = "2.349014"
)

private const val DEFAULT_IMAGE_WIDTH = "250"

/**
 * Annotated with Singleton as the class created a lot of objects.
 * DI: LocalDataSource cannot be provided without an @Inject constructor or an @Provides-annotated method
 */
@Singleton
class LocalDataSource @Inject constructor() {

    // From https://github.com/android/codelab-android-compose/blob/main/AdvancedStateAndSideEffectsCodelab/app/src/main/java/androidx/compose/samples/crane/data/DestinationsLocalDataSource.kt
    val restaurants = listOf(
        Place(
            city = NAPLES,
            description = "1286 Restaurants",
            imageUrl = "https://images.unsplash.com/photo-1534308983496-4fabb1a015ee?ixlib=rb-1.2.1&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = DALLAS,
            description = "2241 Restaurants",
            imageUrl = "https://images.unsplash.com/photo-1495749388945-9d6e4e5b67b1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = CORDOBA,
            description = "876 Restaurants",
            imageUrl = "https://images.unsplash.com/photo-1562625964-ffe9b2f617fc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=250&q=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = MADRID,
            description = "5610 Restaurants",
            imageUrl = "https://images.unsplash.com/photo-1515443961218-a51367888e4b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = MALDIVAS,
            description = "1286 Restaurants",
            imageUrl = "https://images.unsplash.com/flagged/photo-1556202256-af2687079e51?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = ASPEN,
            description = "2241 Restaurants",
            imageUrl = "https://images.unsplash.com/photo-1542384557-0824d90731ee?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = BALI,
            description = "876 Restaurants",
            imageUrl = "https://images.unsplash.com/photo-1567337710282-00832b415979?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        )
    )

    val hotels = listOf(
        Place(
            city = MALDIVAS,
            description = "1286 Available Properties",
            imageUrl = "https://images.unsplash.com/photo-1520250497591-112f2f40a3f4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = ASPEN,
            description = "2241 Available Properties",
            imageUrl = "https://images.unsplash.com/photo-1445019980597-93fa8acb246c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = BALI,
            description = "876 Available Properties",
            imageUrl = "https://images.unsplash.com/photo-1570213489059-0aac6626cade?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = BIGSUR,
            description = "5610 Available Properties",
            imageUrl = "https://images.unsplash.com/photo-1561409037-c7be81613c1f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = NAPLES,
            description = "1286 Available Properties",
            imageUrl = "https://images.unsplash.com/photo-1455587734955-081b22074882?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = DALLAS,
            description = "2241 Available Properties",
            imageUrl = "https://images.unsplash.com/46/sh3y2u5PSaKq8c4LxB3B_submission-photo-4.jpg?ixlib=rb-1.2.1&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = CORDOBA,
            description = "876 Available Properties",
            imageUrl = "https://images.unsplash.com/photo-1570214476695-19bd467e6f7a?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        )
    )

    val destinations = listOf(
        Place(
            city = KHUMBUVALLEY,
            description = "Nonstop - 5h 16m+",
            imageUrl = "https://images.unsplash.com/photo-1544735716-392fe2489ffa?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = MADRID,
            description = "Nonstop - 2h 12m+",
            imageUrl = "https://images.unsplash.com/photo-1539037116277-4db20889f2d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = BALI,
            description = "Nonstop - 6h 20m+",
            imageUrl = "https://images.unsplash.com/photo-1518548419970-58e3b4079ab2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = ROME,
            description = "Nonstop - 2h 38m+",
            imageUrl = "https://images.unsplash.com/photo-1515542622106-78bda8ba0e5b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = GRANADA,
            description = "Nonstop - 2h 12m+",
            imageUrl = "https://images.unsplash.com/photo-1534423839368-1796a4dd1845?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = MALDIVAS,
            description = "Nonstop - 9h 24m+",
            imageUrl = "https://images.unsplash.com/photo-1544550581-5f7ceaf7f992?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = WASHINGTONDC,
            description = "Nonstop - 7h 30m+",
            imageUrl = "https://images.unsplash.com/photo-1557160854-e1e89fdd3286?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = BARCELONA,
            description = "Nonstop - 2h 12m+",
            imageUrl = "https://images.unsplash.com/photo-1562883676-8c7feb83f09b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = CRETE,
            description = "Nonstop - 1h 50m+",
            imageUrl = "https://images.unsplash.com/photo-1486575008575-27670acb58db?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = NAPLES,
            description = "Nonstop - 1h 45m+",
            imageUrl = "https://images.unsplash.com/photo-1534308983496-4fabb1a015ee?ixlib=rb-1.2.1&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = DALLAS,
            description = "Nonstop - 8h 30m+",
            imageUrl = "https://images.unsplash.com/photo-1495749388945-9d6e4e5b67b1?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = CORDOBA,
            description = "1 stop - 11h 30m+",
            imageUrl = "https://images.unsplash.com/photo-1562625964-ffe9b2f617fc?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=250&q=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = BIGSUR,
            description = "Nonstop - 10h 45m+",
            imageUrl = "https://images.unsplash.com/photo-1561409037-c7be81613c1f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = LONDON,
            description = "Nonstop - 1h 5m+",
            imageUrl = "https://images.unsplash.com/photo-1505761671935-60b3a7427bad?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
        Place(
            city = PARIS,
            description = "Nonstop - 2h 25m+",
            imageUrl = "https://images.unsplash.com/photo-1509299349698-dd22323b5963?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=$DEFAULT_IMAGE_WIDTH"
        ),
    )
}