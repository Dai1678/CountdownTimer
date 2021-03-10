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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.dai.countdowntimer.AppState
import dev.dai.countdowntimer.ui.theme.MyTheme

@Composable
fun TimerScreen(state: AppState, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CountdownProgressBar(state.progress)
        Timer(state.hours, state.minutes, state.seconds)
    }
}

@Composable
private fun CountdownProgressBar(progress: Float) {
    CircularProgressIndicator(
        progress = progress,
        modifier = Modifier
            .fillMaxWidth(0.65f)
            .aspectRatio(1f)
    )
}

@Preview
@Composable
private fun TimerScreenPreview() {
    MyTheme {
        Surface {
            TimerScreen(state = AppState())
        }
    }
}
