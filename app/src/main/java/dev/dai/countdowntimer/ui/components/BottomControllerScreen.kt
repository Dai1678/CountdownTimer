package dev.dai.countdowntimer.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.dai.countdowntimer.ui.theme.MyTheme

@Composable
fun BottomControllerScreen(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxWidth()) {
        val (resetButton, fab) = createRefs()
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(resetButton) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                }
                .padding(start = 24.dp)
        ) {
            Text(text = "リセット")
        }

        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(fab) {
                centerHorizontallyTo(parent)
            }
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = ""
            )
        }
    }
}

@Preview
@Composable
private fun BottomControllerPreview() {
    MyTheme {
        Surface {
            BottomControllerScreen()
        }
    }
}
