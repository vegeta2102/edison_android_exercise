package jp.speakbuddy.edisonandroidexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactScreen
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactScreenData
import jp.speakbuddy.edisonandroidexercise.ui.fact.FactViewModel
import jp.speakbuddy.edisonandroidexercise.ui.theme.EdisonAndroidExerciseTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: FactViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val content by viewModel.content.collectAsState(initial = "")
            val length by viewModel.length.collectAsState(initial = 0)
            val isLengthVisible by viewModel.isLengthVisible.collectAsState(initial = false)
            val isMultipleCatVisible by viewModel.isShowMultipleCats.collectAsState(initial = false)
            EdisonAndroidExerciseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FactScreen(
                        factScreenData = FactScreenData(
                            content,
                            length,
                            isLengthVisible,
                            isMultipleCatVisible
                        ),
                        onFetch = {
                            viewModel.fetchFact()
                        }
                    )
                }
            }
        }
    }
}