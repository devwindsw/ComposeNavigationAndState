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
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat

@Composable
fun CustomTabBar(
    modifier: Modifier = Modifier,
    children: @Composable (Modifier) -> Unit
) {
    Row(modifier) {
        children(
            Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun CustomTabs(
    modifier: Modifier = Modifier,
    allScreens: List<Destination>,
    onTabSelected: (Destination) -> Unit,
    currentScreen: Destination
) {
    TabRow(
        selectedTabIndex = getIndexOfTab(allScreens, currentScreen),
        modifier = modifier.height(TabHeight),
        contentColor = MaterialTheme.colorScheme.onSurface,
        indicator = { },
        divider = { }
    ) {
        allScreens.forEachIndexed { index, screen ->
            val selected = currentScreen == screen
            val color = MaterialTheme.colorScheme.primary
            val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
            val animSpec = remember {
                tween<Color>(
                    durationMillis = durationMillis,
                    easing = LinearEasing,
                    delayMillis = TabFadeInAnimationDelay
                )
            }
            val tabTintColor by animateColorAsState(
                targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
                animationSpec = animSpec
            )
            var textModifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            if (selected) {
                textModifier =
                    Modifier
                        .border(BorderStroke(2.dp, Color.Black), RoundedCornerShape(16.dp))
                        .then(textModifier)
            }

            Tab(
                selected = selected,
                onClick = { onTabSelected(screen) }
            ) {
                Text(
                    modifier = textModifier,
                    text = screen.title.uppercase(
                        ConfigurationCompat.getLocales(LocalConfiguration.current)[0]!!
                    ),
                    color = tabTintColor
                )
            }
        }
    }
}

private fun getIndexOfTab(allScreens: List<Destination>, screen: Destination?): Int {
    if (screen == null) {
        Log.w("ComposeNavigation", "getIndexOfTab null screen")
        return 0;
    }
    val index = allScreens.indexOf(screen)
    Log.w("ComposeNavigation", "getIndexOfTab index=${index}")
    if (index < 0 || index >= allScreens.size) return 0
    return index
}

private val TabHeight = 56.dp
private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100