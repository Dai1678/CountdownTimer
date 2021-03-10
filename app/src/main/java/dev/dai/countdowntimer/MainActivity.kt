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
package dev.dai.countdowntimer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.dai.countdowntimer.ui.components.BottomControllerScreen
import dev.dai.countdowntimer.ui.components.EditScreen
import dev.dai.countdowntimer.ui.components.TimerScreen
import dev.dai.countdowntimer.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                AppContent(viewModel)
            }
        }
    }
}

@Composable
fun AppContent(viewModel: MainActivityViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        Column {
            Crossfade(
                targetState = viewModel.countdownTimerState.screenType,
                modifier = Modifier.weight(1f)
            ) { screenType ->
                when (screenType) {
                    ScreenType.Timer -> TimerScreen(state = viewModel.countdownTimerState)
                    ScreenType.Edit -> EditScreen(
                        state = viewModel.countdownTimerState,
                        onClickKey = { number -> viewModel.inputKey(number) },
                        onClickBackKey = { viewModel.backKey() }
                    )
                }
            }
            BottomControllerScreen(
                state = viewModel.countdownTimerState,
                onClickFab = {
                    if (viewModel.countdownTimerState.screenType == ScreenType.Timer) {
                        viewModel.toggleTimer()
                    } else {
                        viewModel.editCompleted()
                    }
                },
                onClickReset = viewModel::resetTimer,
                onClickDelete = viewModel::deleteTimer,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        AppContent(viewModel = MainActivityViewModel())
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        AppContent(viewModel = MainActivityViewModel())
    }
}
