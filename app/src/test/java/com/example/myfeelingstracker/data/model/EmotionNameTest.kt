package com.example.myfeelingstracker.data.model

import com.example.myfeelingstracker.R
import com.google.common.truth.Truth.assertThat
import org.junit.Test

/**
 * Unit tests for EmotionName utility.
 * Tests the mapping between emotion categories, intensity levels, and string resource IDs.
 *
 * Note: These tests verify string resource ID mappings, not the actual localized strings.
 * For testing actual localized strings, use instrumented tests with Context.
 */
class EmotionNameTest {

    @Test
    fun `getStringResId returns correct resource ID for joy and mild intensity`() {
        val result = EmotionName.getStringResId(EmotionCategory.JOY, IntensityLevel.MILD)
        assertThat(result).isEqualTo(R.string.emotion_serenity)
    }

    @Test
    fun `getStringResId returns correct resource ID for joy and moderate intensity`() {
        val result = EmotionName.getStringResId(EmotionCategory.JOY, IntensityLevel.MODERATE)
        assertThat(result).isEqualTo(R.string.emotion_joy_moderate)
    }

    @Test
    fun `getStringResId returns correct resource ID for joy and intense intensity`() {
        val result = EmotionName.getStringResId(EmotionCategory.JOY, IntensityLevel.INTENSE)
        assertThat(result).isEqualTo(R.string.emotion_ecstasy)
    }

    @Test
    fun `getStringResId returns correct resource ID for anger and mild intensity`() {
        val result = EmotionName.getStringResId(EmotionCategory.ANGER, IntensityLevel.MILD)
        assertThat(result).isEqualTo(R.string.emotion_annoyance)
    }

    @Test
    fun `getStringResIdsForCategory returns all three intensities for joy`() {
        val result = EmotionName.getStringResIdsForCategory(EmotionCategory.JOY)

        assertThat(result).hasSize(3)
        assertThat(result[IntensityLevel.MILD]).isEqualTo(R.string.emotion_serenity)
        assertThat(result[IntensityLevel.MODERATE]).isEqualTo(R.string.emotion_joy_moderate)
        assertThat(result[IntensityLevel.INTENSE]).isEqualTo(R.string.emotion_ecstasy)
    }

    @Test
    fun `getStringResIdsForCategory returns all three intensities for all categories`() {
        EmotionCategory.entries.forEach { category ->
            val result = EmotionName.getStringResIdsForCategory(category)
            assertThat(result).hasSize(3)
            assertThat(result).containsKey(IntensityLevel.MILD)
            assertThat(result).containsKey(IntensityLevel.MODERATE)
            assertThat(result).containsKey(IntensityLevel.INTENSE)
        }
    }

    @Test
    fun `all emotion combinations have unique string resource IDs`() {
        val allResIds = mutableSetOf<Int>()

        EmotionCategory.entries.forEach { category ->
            IntensityLevel.entries.forEach { intensity ->
                val resId = EmotionName.getStringResId(category, intensity)
                assertThat(allResIds).doesNotContain(resId)
                allResIds.add(resId)
            }
        }

        // Should have 8 categories × 3 intensities = 24 unique resource IDs
        assertThat(allResIds).hasSize(24)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `getStringResId throws exception for invalid combination`() {
        // This should never happen in production, but test defensive programming
        // Note: Can't actually create invalid enum values, so this documents the behavior
    }
}
