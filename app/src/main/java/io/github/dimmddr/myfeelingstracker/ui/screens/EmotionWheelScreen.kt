package io.github.dimmddr.myfeelingstracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import io.github.dimmddr.myfeelingstracker.R
import io.github.dimmddr.myfeelingstracker.ui.theme.AppSpacings

@Composable
fun EmotionWheelScreen() {
    val context = LocalContext.current
    val imageLoader = remember(context) {
        ImageLoader.Builder(context)
            .components { add(SvgDecoder.Factory()) }
            .build()
    }

    var showCircles by remember { mutableStateOf(false) }

    val svgRes = if (showCircles) R.raw.emotion_wheel_with_circles else R.raw.emotion_wheel

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(svgRes)
                .build(),
            contentDescription = null,
            imageLoader = imageLoader,
            modifier = Modifier
                .padding(horizontal = AppSpacings.Small)
                .fillMaxWidth()
        )
        Button(
            onClick = { showCircles = !showCircles },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = AppSpacings.Medium)
        ) {
            Text(if (showCircles) "No circles" else "Circles")
        }
    }
}
