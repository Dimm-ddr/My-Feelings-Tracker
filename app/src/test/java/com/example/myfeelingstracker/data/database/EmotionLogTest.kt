package com.example.myfeelingstracker.data.database

import com.example.myfeelingstracker.data.model.EmotionCategory
import com.example.myfeelingstracker.data.model.IntensityLevel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Unit tests for EmotionLog data class.
 */
class EmotionLogTest {

    @Test
    fun `EmotionLog creation with all fields`() {
        val timestamp = System.currentTimeMillis()
        val created = timestamp - 1000

        val log = EmotionLog(
            id = 1L,
            emotionCategory = EmotionCategory.JOY,
            intensityLevel = IntensityLevel.MODERATE,
            timestamp = timestamp,
            createdAt = created,
            lastModified = timestamp
        )

        assertThat(log.id).isEqualTo(1L)
        assertThat(log.emotionCategory).isEqualTo(EmotionCategory.JOY)
        assertThat(log.intensityLevel).isEqualTo(IntensityLevel.MODERATE)
        assertThat(log.timestamp).isEqualTo(timestamp)
        assertThat(log.createdAt).isEqualTo(created)
        assertThat(log.lastModified).isEqualTo(timestamp)
    }

    @Test
    fun `EmotionLog creation with defaults`() {
        val log = EmotionLog(
            emotionCategory = EmotionCategory.SADNESS,
            intensityLevel = IntensityLevel.MILD
        )

        assertThat(log.id).isEqualTo(0L) // Default auto-generated
        assertThat(log.emotionCategory).isEqualTo(EmotionCategory.SADNESS)
        assertThat(log.intensityLevel).isEqualTo(IntensityLevel.MILD)
        assertThat(log.timestamp).isGreaterThan(0L) // Should have current time
        assertThat(log.createdAt).isGreaterThan(0L)
        assertThat(log.lastModified).isNull() // Never edited
    }

    @Test
    fun `EmotionLog with null lastModified indicates never edited`() {
        val log = EmotionLog(
            emotionCategory = EmotionCategory.ANGER,
            intensityLevel = IntensityLevel.INTENSE,
            lastModified = null
        )

        assertThat(log.lastModified).isNull()
    }

    @Test
    fun `EmotionLog data class equality`() {
        val timestamp = 1234567890L
        val log1 = EmotionLog(
            id = 1L,
            emotionCategory = EmotionCategory.FEAR,
            intensityLevel = IntensityLevel.MODERATE,
            timestamp = timestamp,
            createdAt = timestamp,
            lastModified = null
        )

        val log2 = EmotionLog(
            id = 1L,
            emotionCategory = EmotionCategory.FEAR,
            intensityLevel = IntensityLevel.MODERATE,
            timestamp = timestamp,
            createdAt = timestamp,
            lastModified = null
        )

        assertThat(log1).isEqualTo(log2)
    }

    @Test
    fun `EmotionLog data class copy`() {
        val original = EmotionLog(
            id = 1L,
            emotionCategory = EmotionCategory.TRUST,
            intensityLevel = IntensityLevel.MILD,
            timestamp = 1000L,
            createdAt = 1000L,
            lastModified = null
        )

        val modified = original.copy(
            intensityLevel = IntensityLevel.INTENSE,
            lastModified = 2000L
        )

        assertThat(modified.id).isEqualTo(original.id)
        assertThat(modified.emotionCategory).isEqualTo(original.emotionCategory)
        assertThat(modified.intensityLevel).isEqualTo(IntensityLevel.INTENSE)
        assertThat(modified.lastModified).isEqualTo(2000L)
    }
}
