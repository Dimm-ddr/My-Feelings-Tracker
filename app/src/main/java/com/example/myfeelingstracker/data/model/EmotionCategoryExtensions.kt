package com.example.myfeelingstracker.data.model

/**
 * Extension functions and utilities for EmotionCategory.
 * 
 * These helper functions will remain useful when blended emotions are added.
 */

/**
 * Returns whether this emotion category is a primary emotion (has intensity levels).
 * 
 * Currently all emotions are primary. When blended emotions are added, this will
 * return false for LOVE, SUBMISSION, AWE, DISAPPROVAL, REMORSE, CONTEMPT,
 * AGGRESSIVENESS, and OPTIMISM.
 */
fun EmotionCategory.isPrimary(): Boolean = true // All are primary for now

/**
 * Returns whether this emotion category is a blended emotion (no intensity levels).
 * 
 * Currently returns false for all emotions. When blended emotions are added,
 * this will return true for the 8 blended types.
 */
fun EmotionCategory.isBlended(): Boolean = false // No blended emotions yet

/**
 * Validates whether the given intensity level is valid for this emotion category.
 * 
 * @return true if valid, false otherwise
 */
fun EmotionCategory.isValidIntensity(intensity: IntensityLevel?): Boolean {
    return when {
        isPrimary() -> intensity != null  // Primary emotions require intensity
        isBlended() -> intensity == null  // Blended emotions must not have intensity
        else -> false
    }
}

/**
 * Helper object for working with emotion categories.
 */
object EmotionCategories {
    /**
     * Returns a list of all primary emotion categories.
     * Useful for UI that needs to show only primary emotions.
     */
    fun primaryEmotions(): List<EmotionCategory> =
        EmotionCategory.values().filter { it.isPrimary() }

    /**
     * Returns a list of all blended emotion categories.
     * Currently returns empty list. Will return 8 emotions when blended support is added.
     */
    fun blendedEmotions(): List<EmotionCategory> =
        EmotionCategory.values().filter { it.isBlended() }
}
