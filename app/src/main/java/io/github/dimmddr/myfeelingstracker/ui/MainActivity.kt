package io.github.dimmddr.myfeelingstracker.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import io.github.dimmddr.myfeelingstracker.ui.screens.EmotionWheelScreen
import io.github.dimmddr.myfeelingstracker.ui.theme.MyFeelingsTrackerTheme

/**
 * Main Activity using Jetpack Compose.
 * Empty Compose Activity as specified.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyFeelingsTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Modifier.EmotionWheelScreen()
                }
            }
        }
    }
}
