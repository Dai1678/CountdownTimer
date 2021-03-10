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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.dai.countdowntimer.AppState
import dev.dai.countdowntimer.ui.theme.MyTheme

@Composable
fun EditScreen(
    state: AppState,
    onClickKey: (Int) -> Unit,
    onClickBackKey: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(120.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Timer(state.hours, state.minutes, state.seconds)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.Backspace,
                contentDescription = "",
                modifier = Modifier
                    .clickable(onClick = onClickBackKey)
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Divider(modifier = Modifier.padding(horizontal = 24.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Keypad(onClickKey = onClickKey)
    }
}

@Composable
fun Keypad(onClickKey: (Int) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
        for (i in 0..6 step 3) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (j in 1..3) {
                    Key(number = i + j, onClickKey = onClickKey)
                }
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Key(number = 0, onClickKey = onClickKey)
        }
    }
}

@Composable
private fun Key(
    number: Int,
    onClickKey: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable(onClick = { onClickKey(number) })
            .width(80.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.h4
        )
    }
}

@Preview
@Composable
private fun EditScreenPreview() {
    MyTheme {
        Surface {
            EditScreen(
                state = AppState(),
                onClickKey = {},
                onClickBackKey = {}
            )
        }
    }
}
