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

package com.devwindsw.composenavigationandstate.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.SolidColor

// https://developer.android.com/codelabs/jetpack-compose-advanced-state-side-effects#6

/* State holders always need to be remembered in order to keep them in the Composition
 * and not create a new one every time.
 * It's a good practice to create a method in the same file that does this to remove boilerplate
 * and avoid any mistakes that might occur. */
@Composable
fun rememberEditableUserInputState(hint: String): EditableUserInputState =
    // If you only remember this state, it won't survive activity recreations.
    rememberSaveable(hint, saver = EditableUserInputState.Saver) {
        EditableUserInputState(hint, hint)
    }

//  a regular class as state holder
class EditableUserInputState(private val hint: String, initialText: String) {

    var text by mutableStateOf(initialText)
        private set

    fun updateText(newText: String) {
        text = newText
    }

    val isHint: Boolean
        get() = text == hint

    /* rememberSaveable does all this with no extra work for objects
     * that can be stored inside a Bundle, which implement Parcelable.
     * That's not the case for the EditableUserInputState class.
     * Instead, you need to tell rememberSaveable
     * how to save and restore an instance of this class using a Saver
     */
    companion object {
        val Saver: Saver<EditableUserInputState, *> = listSaver(
            save = { listOf(it.hint, it.text) },
            restore = {
                EditableUserInputState(
                    hint = it[0],
                    initialText = it[1],
                )
            }
        )
    }
}

@Composable
fun EditableUserInput(
    state: EditableUserInputState = rememberEditableUserInputState(""),
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null
) {
    BaseUserInput(
        caption = caption,
        tintIcon = { !state.isHint },
        showCaption = { !state.isHint },
        vectorImageId = vectorImageId
    ) {
        BasicTextField(
            value = state.text,
            onValueChange = { state.updateText(it) },
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = LocalContentColor.current),
            cursorBrush = SolidColor(LocalContentColor.current)
        )
    }
}