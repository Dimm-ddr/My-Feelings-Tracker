package com.example.myfeelingstracker.data.model

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

/**
 * Unit tests for EmotionName structure.
 * These tests avoid duplicating the full mapping contract from production code.
 */
class EmotionNameTest {

    @Test
    fun `getStringResIdsForCategory returns exactly all intensity levels for every category`() {
        EmotionCategory.entries.forEach { category ->
            val result = EmotionName.getStringResIdsForCategory(category)
            assertThat(result.keys).containsExactlyElementsIn(IntensityLevel.entries)
        }
    }

    @Test
    fun `all category and intensity combinations map to unique resource IDs`() {
        val allResIds = mutableSetOf<Int>()

        EmotionCategory.entries.forEach { category ->
            IntensityLevel.entries.forEach { intensity ->
                val resId = EmotionName.getStringResId(category, intensity)
                assertThat(allResIds).doesNotContain(resId)
                allResIds.add(resId)
            }
        }

        assertThat(allResIds).hasSize(EmotionCategory.entries.size * IntensityLevel.entries.size)
    }

    @Test
    fun `resolveStringResId throws IllegalArgumentException when pair is missing from source map`() {
        val categoryUnderTest = EmotionCategory.JOY
        val intensityUnderTest = IntensityLevel.MODERATE
        val completeSource =
            EmotionCategory.entries.associateWith { category ->
                EmotionName.getStringResIdsForCategory(category)
            }
        val brokenSource =
            completeSource.toMutableMap().apply {
                put(
                    categoryUnderTest,
                    completeSource
                        .getValue(categoryUnderTest)
                        .filterKeys { intensity -> intensity != intensityUnderTest }
                )
            }

        val exception =
            assertThrows(IllegalArgumentException::class.java) {
                EmotionName.resolveStringResId(categoryUnderTest, intensityUnderTest, brokenSource)
            }

        assertThat(exception)
            .hasMessageThat()
            .contains("Invalid emotion combination: $categoryUnderTest, $intensityUnderTest")
    }
}
