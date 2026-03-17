package io.github.dimmddr.myfeelingstracker.ui.screens

import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import io.github.dimmddr.myfeelingstracker.R
import io.github.dimmddr.myfeelingstracker.data.model.EmotionCategory
import io.github.dimmddr.myfeelingstracker.ui.theme.AppSpacings
import io.github.dimmddr.myfeelingstracker.ui.wheel.BLENDED_SECTOR_BORDERS
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// Fraction of image width from center to label. Tune to move labels in/out along petals.
private const val TEXT_RADIUS_FRACTION = 0.27f

@Composable
fun EmotionWheelScreen() {
    val context = LocalContext.current
    val imageLoader = remember(context) {
        ImageLoader.Builder(context)
            .components { add(SvgDecoder.Factory()) }
            .build()
    }
    val textMeasurer = rememberTextMeasurer()

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
        Canvas(modifier = Modifier.fillMaxSize()) {
            val imageWidthPx = size.width - AppSpacings.Small.toPx() * 2
            val textRadiusPx = imageWidthPx * TEXT_RADIUS_FRACTION
            val labelStyle = TextStyle(
                fontSize = (imageWidthPx * 0.038f / density).sp,
                fontWeight = FontWeight.Medium
            )
            val centerX = size.width / 2f
            val centerY = size.height / 2f

            EmotionCategory.entries.forEachIndexed { index, category ->
                val angle = BLENDED_SECTOR_BORDERS[index]
                val readableAngle = if (angle > PI / 2 || angle < -PI / 2) angle + PI else angle
                val x = centerX + (textRadiusPx * cos(angle)).toFloat()
                val y = centerY + (textRadiusPx * sin(angle)).toFloat()

                val label = category.name.lowercase().replaceFirstChar { it.uppercase() }
                val measured = textMeasurer.measure(label, labelStyle)

                withTransform({
                    rotate(Math.toDegrees(readableAngle).toFloat(), pivot = Offset(x, y))
                }) {
                    drawText(
                        textLayoutResult = measured,
                        color = Color.Black,
                        topLeft = Offset(
                            x - measured.size.width / 2f,
                            y - measured.size.height / 2f
                        )
                    )
                }
            }
        }
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
