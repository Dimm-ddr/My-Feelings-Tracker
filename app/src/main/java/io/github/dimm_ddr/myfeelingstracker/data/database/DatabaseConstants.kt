package io.github.dimm_ddr.myfeelingstracker.data.database

/**
 * Constants for database configuration.
 * Centralized to ensure consistency across database, DAOs, and backup configuration.
 */
object DatabaseConstants {
    /**
     * Name of the Room database file.
     * This must match the database name in:
     * - EmotionDatabase.kt (Room.databaseBuilder)
     * - res/xml/backup_rules.xml
     * - res/xml/data_extraction_rules.xml
     */
    const val DATABASE_NAME = "emotion_database"

    /**
     * Name of the emotion logs table.
     * This must match the tableName in EmotionLog entity and SQL queries.
     */
    const val TABLE_EMOTION_LOGS = "emotion_logs"
}
