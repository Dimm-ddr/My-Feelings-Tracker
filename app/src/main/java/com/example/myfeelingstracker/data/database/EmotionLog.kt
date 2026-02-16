package com.example.myfeelingstracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myfeelingstracker.data.model.EmotionCategory
import com.example.myfeelingstracker.data.model.IntensityLevel

/**
 * Entity representing an emotion log entry.
 * Based on Plutchik's Wheel of Emotions with 8 categories and 3 intensity levels.
 *
 * @property id Unique identifier (auto-generated)
 * @property emotionCategory One of 8 primary emotions from Plutchik's wheel
 * @property intensityLevel One of 3 levels: MILD, MODERATE, or INTENSE
 * @property timestamp When the emotion was logged (in milliseconds, UTC)
 * @property createdAt When the record was created (for tracking)
 * @property lastModified When the record was last edited (null if never edited)
 *
 * FUTURE EXPANSION: To support Plutchik's 8 blended emotions (Love, Submission, Awe, etc.):
 * - Add blended emotion types to EmotionCategory enum
 * - Change intensityLevel to nullable (requires Room migration v1→v2)
 * - Validation: intensityLevel must be null for blended emotions, non-null for primary
 */
@Entity(tableName = DatabaseConstants.TABLE_EMOTION_LOGS)
data class EmotionLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val emotionCategory: EmotionCategory,
    val intensityLevel: IntensityLevel,
    val timestamp: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis(),
    val lastModified: Long? = null
)
