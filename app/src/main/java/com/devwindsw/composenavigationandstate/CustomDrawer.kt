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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devwindsw.composenavigationandstate.ui.theme.ComposeNavigationAndStateTheme

private val items = listOf("Find Trips", "My Trips", "Saved Trips", "Price Alerts", "My Account")

@Composable
fun CustomDrawer(modifier: Modifier = Modifier) {
    ModalDrawerSheet {
        Row {
            Image(
                painter = painterResource(R.drawable.ic_crane_drawer),
                contentDescription = stringResource(R.string.cd_menu)
            )
            Text("Drawer title", modifier = Modifier.padding(16.dp))
        }
        HorizontalDivider()
        for (item in items) {
            NavigationDrawerItem(
                label = { Text(text = item) },
                selected = false,
                onClick = { /*TODO*/ }
            )
        }
    }
}