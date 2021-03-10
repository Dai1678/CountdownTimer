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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import dev.dai.countdowntimer.AppState
import dev.dai.countdowntimer.ScreenType
import dev.dai.countdowntimer.TimerState
import dev.dai.countdowntimer.ui.theme.MyTheme

@Composable
fun BottomControllerScreen(
    state: AppState,
    onClickReset: () -> Unit,
    onClickDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val isTimerScreen = state.screenType == ScreenType.Timer
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = { onClickReset() },
            modifier = Modifier.alpha(if (isTimerScreen) 1f else 0f),
        ) {
            Text(text = "リセット")
        }

        TextButton(
            onClick = { onClickDelete() },
            modifier = Modifier.alpha(if (isTimerScreen) 1f else 0f)
        ) {
            Text(text = "削除")
        }
    }
}

@Preview
@Composable
private fun BottomControllerPreview() {
    MyTheme {
        Surface {
            BottomControllerScreen(
                state = AppState(),
                onClickReset = {},
                onClickDelete = {}
            )
        }
    }
}
