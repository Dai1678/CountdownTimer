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

import android.os.CountDownTimer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.ceil

class MainActivityViewModel : ViewModel() {

    // タイマーの各時間(00:00:00)を管理する値
    private var timerNumArrayDeque = ArrayDeque(listOf(0, 0, 0, 0, 0, 0))

    private var countDownTimer: CountDownTimer? = null
    // タイマー計測時間
    private var totalTime: Long = 0
    // 経過時間
    private var elapsedTime: Long = 0

    var countdownTimerState by mutableStateOf(AppState())
        private set

    fun inputKey(number: Int) {
        if (timerNumArrayDeque.first() > 0) return
        timerNumArrayDeque.removeFirst()
        timerNumArrayDeque.addLast(number)
        updateTimer()
    }

    fun backKey() {
        timerNumArrayDeque.removeLast()
        timerNumArrayDeque.addFirst(0)
        updateTimer()
    }

    // タイマー時間の入力完了後の処理
    fun editCompleted() {
        if (getTotalTime() == 0L) return
        // タイマー画面を表示
        countdownTimerState = countdownTimerState.copy(screenType = ScreenType.Timer)
        // 入力した時間(ms)を設定
        totalTime = getTotalTime()
        // タイマー開始
        startTimer(totalTime)
    }

    fun resetTimer() {
        // タイマー中止
        pauseTimer()
        // 入力時の時間に再設定
        totalTime = getTotalTime()
        updateTimerNumArrayDeque(totalTime)
        // 経過時間を初期化
        elapsedTime = 0
        // 状態をリセット
        updateTimer()
        resetProgress()
    }

    fun deleteTimer() {
        // タイマー中止
        pauseTimer()
        // 入力時間を初期化
        updateTimerNumArrayDeque(0)
        // 経過時間をリセット
        elapsedTime = 0
        // 状態初期化・入力画面を表示
        countdownTimerState = AppState()
    }

    fun toggleTimer() = when (countdownTimerState.timerState) {
        TimerState.STARTED -> pauseTimer()
        TimerState.PAUSED -> {
            if (elapsedTime == 0L) {
                // リセットした場合は最初からスタート
                startTimer(totalTime)
            } else {
                // ポーズした場合は経過時間から再スタート
                startTimer(elapsedTime)
            }
        }
        TimerState.FINISHED -> resetTimer()
    }

    private fun startTimer(count: Long) {
        countdownTimerState = countdownTimerState.copy(timerState = TimerState.STARTED)
        countDownTimer = object : CountDownTimer(count, 1) {
            override fun onTick(millisUntilFinished: Long) {
                val tickTime = ceil(millisUntilFinished / 1000.0).toInt()
                val hours = tickTime / 60 / 60 % 24
                val minutes = tickTime / 60 % 60
                val seconds = tickTime % 60
                val progress = millisUntilFinished.toFloat() / totalTime.toFloat()
                countdownTimerState = countdownTimerState.copy(
                    hours = hours,
                    minutes = minutes,
                    seconds = seconds,
                    progress = progress
                )
                elapsedTime = millisUntilFinished
            }

            override fun onFinish() {
                countdownTimerState = countdownTimerState.copy(
                    timerState = TimerState.FINISHED,
                    hours = 0,
                    minutes = 0,
                    seconds = 0,
                    progress = 0f
                )
            }
        }.apply {
            start()
        }
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
        countdownTimerState = countdownTimerState.copy(timerState = TimerState.PAUSED)
    }

    private fun updateTimer() {
        countdownTimerState = countdownTimerState.copy(
            hours = timerNumArrayDeque[0] * 10 + timerNumArrayDeque[1],
            minutes = timerNumArrayDeque[2] * 10 + timerNumArrayDeque[3],
            seconds = timerNumArrayDeque[4] * 10 + timerNumArrayDeque[5]
        )
    }

    private fun resetProgress() {
        countdownTimerState = countdownTimerState.copy(progress = 1.0f)
    }

    // 入力時間の更新
    private fun updateTimerNumArrayDeque(millisUntilFinished: Long) {
        val tickTime = ceil(millisUntilFinished / 1000.0).toInt()
        val hours = tickTime / 60 / 60 % 24
        val minutes = tickTime / 60 % 60
        val seconds = tickTime % 60
        timerNumArrayDeque = ArrayDeque(
            listOf(hours / 10, hours % 10, minutes / 10, minutes % 10, seconds / 10, seconds % 10)
        )
    }

    // 入力した時間から継続時間の合計を返す
    private fun getTotalTime(): Long {
        val hour = (timerNumArrayDeque[0] * 10 + timerNumArrayDeque[1]).toLong()
        val minutes = (timerNumArrayDeque[2] * 10 + timerNumArrayDeque[3]).toLong()
        val seconds = (timerNumArrayDeque[4] * 10 + timerNumArrayDeque[5]).toLong()
        return 1000 * 60 * 60 * hour + 1000 * 60 * minutes + 1000 * seconds
    }
}
