package io.github.dimm_ddr.myfeelingstracker.data.database

import androidx.room.TypeConverter
import io.github.dimm_ddr.myfeelingstracker.data.model.EmotionCategory
import io.github.dimm_ddr.myfeelingstracker.data.model.IntensityLevel

/**
 * Room TypeConverters for enum types.
 * Converts between enum types and their string representations for database storage.
 */
class Converters {
    @TypeConverter
    fun fromEmotionCategory(value: EmotionCategory): String = value.name

    @TypeConverter
    fun toEmotionCategory(value: String): EmotionCategory = EmotionCategory.valueOf(value)

    @TypeConverter
    fun fromIntensityLevel(value: IntensityLevel): String = value.name

    @TypeConverter
    fun toIntensityLevel(value: String): IntensityLevel = IntensityLevel.valueOf(value)
}
