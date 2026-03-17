package io.github.dimmddr.myfeelingstracker.ui.wheel

import io.github.dimmddr.myfeelingstracker.data.model.EmotionCategory
import kotlin.math.atan2

// Primary sector borders, evenly spaced starting from -3π/4 (step = π/4)
val SECTOR_BORDERS = doubleArrayOf(
    -2.356194490192345,
    -1.570796326794896,
    -0.785398163397448,
    0.0,
    0.785398163397448,
    1.570796326794896,
    2.356194490192345,
    3.141592653589793
)

// Blended emotion sector borders, offset by π/8 from primary borders (step = π/4)
val BLENDED_SECTOR_BORDERS = doubleArrayOf(
    -2.748893571891069,
    -1.963495408493620,
    -1.178097245096172,
    -0.392699081698724,
    0.392699081698724,
    1.178097245096172,
    1.963495408493621,
    2.748893571891068
)

// Returns the sector index (0 until SECTOR_BORDERS.size) for a given angle in radians.
// The angle is expected as returned by atan2 (range -π to π).
fun sectorForAngle(angle: Double): Int {
    for (i in 0 until SECTOR_BORDERS.size) {
        if (angle < SECTOR_BORDERS[i]) return i
    }
    return 0
}

fun sectorForAngle(angle: Float): Int = sectorForAngle(angle.toDouble())

// Returns the EmotionCategory for a given angle in radians.
fun categoryForAngle(angle: Double): EmotionCategory =
    EmotionCategory.entries[sectorForAngle(angle)]

// Returns the sector index for a tap offset relative to the wheel center.
// dx and dy are the horizontal and vertical distances from center (positive Y is down).
fun sectorForOffset(dx: Float, dy: Float): Int = sectorForAngle(atan2(dy, dx))

// Returns the EmotionCategory for a tap offset relative to the wheel center.
fun categoryForOffset(dx: Float, dy: Float): EmotionCategory =
    EmotionCategory.entries[sectorForOffset(dx, dy)]
