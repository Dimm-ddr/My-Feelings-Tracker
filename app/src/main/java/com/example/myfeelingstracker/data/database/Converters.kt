package com.example.myfeelingstracker.data.database

import androidx.room.TypeConverter
import com.example.myfeelingstracker.data.model.EmotionCategory
import com.example.myfeelingstracker.data.model.IntensityLevel

/**
 * Room TypeConverters for enum types.
 * Converts between enum types and their string representations for database storage.
 */
class Converters {
    @TypeConverter
    fun fromEmotionCategory(value: EmotionCategory): String {
        return value.name
    }

    @TypeConverter
    fun toEmotionCategory(value: String): EmotionCategory {
        return EmotionCategory.valueOf(value)
    }

    @TypeConverter
    fun fromIntensityLevel(value: IntensityLevel): String {
        return value.name
    }

    @TypeConverter
    fun toIntensityLevel(value: String): IntensityLevel {
        return IntensityLevel.valueOf(value)
    }
}
