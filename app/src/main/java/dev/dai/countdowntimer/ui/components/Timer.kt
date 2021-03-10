/*
 * Copyright 2021 The Android Open Source Project
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
package dev.dai.countdowntimer.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Timer(hour: Int, minutes: Int, seconds: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TimeText(value = hour, unit = "h")
        TimeText(value = minutes, unit = "m")
        TimeText(value = seconds, unit = "s")
    }
}

@Composable
private fun TimeText(value: Int, unit: String) {
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(
            text = value.toString().padStart(2, '0'),
            style = MaterialTheme.typography.h3,
            modifier = Modifier.alignByBaseline()
        )
        Text(
            text = unit,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.alignByBaseline()
        )
    }
}
