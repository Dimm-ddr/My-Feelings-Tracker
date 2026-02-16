package com.example.myfeelingstracker.data.model

import android.content.Context
import androidx.annotation.StringRes
import com.example.myfeelingstracker.R
import kotlin.collections.mapOf
import kotlin.to

/**
 * Maps emotion categories and intensity levels to their localized string resources
 * according to Plutchik's Wheel of Emotions.
 * 
 * This uses string resource IDs to ensure proper localization support.
 */
object EmotionName {
    private val emotionStringResIds = mapOf(
        EmotionCategory.JOY to mapOf(
            IntensityLevel.MILD to R.string.emotion_serenity,
            IntensityLevel.MODERATE to R.string.emotion_joy_moderate,
            IntensityLevel.INTENSE to R.string.emotion_ecstasy
        ),
        EmotionCategory.SADNESS to mapOf(
            IntensityLevel.MILD to R.string.emotion_pensiveness,
            IntensityLevel.MODERATE to R.string.emotion_sadness_moderate,
            IntensityLevel.INTENSE to R.string.emotion_grief
        ),
        EmotionCategory.ANGER to mapOf(
            IntensityLevel.MILD to R.string.emotion_annoyance,
            IntensityLevel.MODERATE to R.string.emotion_anger_moderate,
            IntensityLevel.INTENSE to R.string.emotion_rage
        ),
        EmotionCategory.FEAR to mapOf(
            IntensityLevel.MILD to R.string.emotion_apprehension,
            IntensityLevel.MODERATE to R.string.emotion_fear_moderate,
            IntensityLevel.INTENSE to R.string.emotion_terror
        ),
        EmotionCategory.TRUST to mapOf(
            IntensityLevel.MILD to R.string.emotion_acceptance,
            IntensityLevel.MODERATE to R.string.emotion_trust_moderate,
            IntensityLevel.INTENSE to R.string.emotion_admiration
        ),
        EmotionCategory.DISGUST to mapOf(
            IntensityLevel.MILD to R.string.emotion_boredom,
            IntensityLevel.MODERATE to R.string.emotion_disgust_moderate,
            IntensityLevel.INTENSE to R.string.emotion_loathing
        ),
        EmotionCategory.ANTICIPATION to mapOf(
            IntensityLevel.MILD to R.string.emotion_interest,
            IntensityLevel.MODERATE to R.string.emotion_anticipation_moderate,
            IntensityLevel.INTENSE to R.string.emotion_vigilance
        ),
        EmotionCategory.SURPRISE to mapOf(
            IntensityLevel.MILD to R.string.emotion_distraction,
            IntensityLevel.MODERATE to R.string.emotion_surprise_moderate,
            IntensityLevel.INTENSE to R.string.emotion_amazement
        )
    )

    /**
     * Get the string resource ID for a specific emotion.
     * Use with Context.getString() to get the localized name.
     * 
     * @return String resource ID
     * @throws IllegalArgumentException if the combination is invalid
     */
    @StringRes
    fun getStringResId(category: EmotionCategory, intensity: IntensityLevel): Int {
        return emotionStringResIds[category]?.get(intensity)
            ?: throw IllegalArgumentException("Invalid emotion combination: $category, $intensity")
    }

    /**
     * Get the localized emotion name for a category and intensity combination.
     * 
     * @param context Android context for accessing string resources
     * @param category One of 8 primary emotions
     * @param intensity One of 3 intensity levels
     * @return Localized emotion name (e.g., "Serenity", "Joy", "Ecstasy")
     */
    fun getName(context: Context, category: EmotionCategory, intensity: IntensityLevel): String {
        return context.getString(getStringResId(category, intensity))
    }

    /**
     * Get all string resource IDs for a specific category across all intensities.
     * 
     * @return Map of intensity levels to string resource IDs
     * @throws IllegalArgumentException if the category is invalid
     */
    fun getStringResIdsForCategory(category: EmotionCategory): Map<IntensityLevel, Int> {
        return emotionStringResIds[category]
            ?: throw IllegalArgumentException("Invalid emotion category: $category")
    }

    /**
     * Get all localized emotion names for a specific category across all intensities.
     * 
     * @param context Android context for accessing string resources
     * @param category One of 8 primary emotions
     * @return Map of intensity levels to localized emotion names
     */
    fun getNamesForCategory(context: Context, category: EmotionCategory): Map<IntensityLevel, String> {
        return getStringResIdsForCategory(category).mapValues { (_, resId) ->
            context.getString(resId)
        }
    }
}
