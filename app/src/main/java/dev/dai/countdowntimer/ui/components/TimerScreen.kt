package dev.dai.countdowntimer.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.dai.countdowntimer.ui.theme.MyTheme

@Composable
fun TimerScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CountdownProgressBar()
        Timer()
    }
}

@Composable
private fun CountdownProgressBar() {
    CircularProgressIndicator(
        progress = 1.0f,
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
            TimerScreen()
        }
    }
}
