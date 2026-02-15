package com.example.myfeelingstracker.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing an emotion log entry.
 * Based on Plutchik's wheel of emotions.
 */
@Entity(tableName = "emotion_logs")
data class EmotionLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val emotion: String,
    val intensity: Int, // 1-10 scale
    val timestamp: Long = System.currentTimeMillis(),
    val note: String? = null
)
