package jp.speakbuddy.edisonandroidexercise.ui.fact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonAndroidExerciseTheme

@Composable
fun FactScreen(
    factScreenData: FactScreenData,
    onFetch: (() -> Unit)?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = "Fact",
            style = MaterialTheme.typography.titleLarge
        )

        if (factScreenData.isMultipleCatsVisible) {
            Text(
                text = "Multiple cats!!",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Text(
            text = factScreenData.content,
            style = MaterialTheme.typography.bodyLarge
        )

        Box(
            modifier = Modifier.align(Alignment.End)
        ) {
            if (factScreenData.isLengthVisible) {
                Text(
                    text = "Length: ${factScreenData.length}",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        Button(onClick = {
            onFetch?.invoke()
        }) {
            Text(text = "Update fact")
        }
    }
}

@Preview(showBackground = true, name = "Pixel-8", widthDp = 800, heightDp = 1080)
@Composable
private fun FactScreenPreview() {
    EdisonAndroidExerciseTheme {
        FactScreen(factScreenData = FactScreenData("asdf", 1, true, true), onFetch = {

        })
    }
}
