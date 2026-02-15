package com.example.myfeelingstracker.data.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests for EmotionName with actual Context.
 * Tests that localized strings are properly retrieved.
 */
@RunWith(AndroidJUnit4::class)
class EmotionNameInstrumentedTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun getName_returnsNonEmptyString() {
        val result = EmotionName.getName(context, EmotionCategory.JOY, IntensityLevel.MILD)
        assertThat(result).isNotEmpty()
    }

    @Test
    fun getName_returnsCorrectStringForJoyMild() {
        // Test that we get the expected English string (default locale in tests)
        val result = EmotionName.getName(context, EmotionCategory.JOY, IntensityLevel.MILD)
        assertThat(result).isEqualTo("Serenity")
    }

    @Test
    fun getNamesForCategory_returnsThreeNonEmptyStrings() {
        val result = EmotionName.getNamesForCategory(context, EmotionCategory.ANGER)
        
        assertThat(result).hasSize(3)
        result.values.forEach { name ->
            assertThat(name).isNotEmpty()
        }
    }

    @Test
    fun allEmotionCombinations_haveUniqueLocalizedNames() {
        val allNames = mutableSetOf<String>()
        
        EmotionCategory.values().forEach { category ->
            IntensityLevel.values().forEach { intensity ->
                val name = EmotionName.getName(context, category, intensity)
                assertThat(allNames).doesNotContain(name)
                allNames.add(name)
            }
        }
        
        // Should have 8 categories × 3 intensities = 24 unique names
        assertThat(allNames).hasSize(24)
    }

    @Test
    fun allEmotionNames_areNotResourceIds() {
        // Verify we're getting actual strings, not unresolved resource IDs
        EmotionCategory.values().forEach { category ->
            IntensityLevel.values().forEach { intensity ->
                val name = EmotionName.getName(context, category, intensity)
                // Unresolved resource IDs look like "No resource found for 0x..."
                assertThat(name).doesNotContain("No resource found")
                assertThat(name).doesNotStartWith("0x")
            }
        }
    }
}
