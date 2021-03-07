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
fun Timer() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TimeText(value = 1, unit = "h")
        TimeText(value = 0, unit = "m")
        TimeText(value = 0, unit = "s")
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