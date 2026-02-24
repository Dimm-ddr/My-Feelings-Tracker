package io.github.dimmddr.myfeelingstracker.data.model

/**
 * The 8 primary emotion categories from Plutchik's Wheel of Emotions.
 * Each category represents a family of emotions with three intensity levels.
 *
 * FUTURE EXPANSION: Plutchik's wheel also includes 8 blended emotions on the outer ring,
 * formed by combining adjacent primary emotions:
 * - Joy + Trust = Love
 * - Trust + Fear = Submission
 * - Fear + Surprise = Awe
 * - Surprise + Sadness = Disapproval
 * - Sadness + Disgust = Remorse
 * - Disgust + Anger = Contempt
 * - Anger + Anticipation = Aggressiveness
 * - Anticipation + Joy = Optimism
 *
 * To add blended emotions later:
 * 1. Add 8 new enum values here (LOVE, SUBMISSION, etc.)
 * 2. Make EmotionLog.intensityLevel nullable (requires Room migration)
 * 3. Rule: blended emotions have null intensityLevel
 * 4. Update EmotionName utility to handle blended emotions
 */
enum class EmotionCategory {
    JOY, // Serenity -> Joy -> Ecstasy
    SADNESS, // Pensiveness -> Sadness -> Grief
    ANGER, // Annoyance -> Anger -> Rage
    FEAR, // Apprehension -> Fear -> Terror
    TRUST, // Acceptance -> Trust -> Admiration
    DISGUST, // Boredom -> Disgust -> Loathing
    ANTICIPATION, // Interest -> Anticipation -> Vigilance
    SURPRISE // Distraction -> Surprise -> Amazement
}
