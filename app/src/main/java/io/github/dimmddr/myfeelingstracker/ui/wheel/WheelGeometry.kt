package io.github.dimmddr.myfeelingstracker.ui.wheel

import kotlin.math.PI
import kotlin.math.atan2

// Number of primary emotion sectors.
// Changing this value automatically adjusts all derived geometry.
const val SECTOR_COUNT = 8

// Angular width of each sector in radians: 2π / 8 = π/4
const val SECTOR_ANGLE = 2.0 * PI / SECTOR_COUNT

// Borders between sectors in radians, evenly spaced starting from 0.
// Sector i covers the range [SECTOR_BORDERS[i], SECTOR_BORDERS[(i+1) % SECTOR_COUNT]).
//
// Computed values (i * π/4):
//   0: 0.0000000000000000
//   1: 0.7853981633974483
//   2: 1.5707963267948966
//   3: 2.3561944901923448
//   4: 3.1415926535897931
//   5: 3.9269908169872414
//   6: 4.7123889803846897
//   7: 5.4977871437821380
val SECTOR_BORDERS = DoubleArray(SECTOR_COUNT) { i -> i * SECTOR_ANGLE }

// Returns the sector index (0 until SECTOR_COUNT) for a given angle in radians.
// The angle is expected as returned by atan2 (range -π to π).
fun sectorForAngle(angle: Double): Int {
    val normalized = (angle + 2.0 * PI) % (2.0 * PI)
    return (normalized / SECTOR_ANGLE).toInt()
}

// Convenience overload accepting Float (as returned by Compose Offset / atan2 with Float args).
fun sectorForAngle(angle: Float): Int = sectorForAngle(angle.toDouble())

// Returns the angle in radians of the center of the given sector.
fun sectorCenterAngle(sector: Int): Double =
    SECTOR_BORDERS[sector % SECTOR_COUNT] + SECTOR_ANGLE / 2.0

// Returns the sector index for a tap offset relative to the wheel center.
// dx and dy are the horizontal and vertical distances from center (positive Y is down).
fun sectorForOffset(dx: Float, dy: Float): Int = sectorForAngle(atan2(dy, dx))
