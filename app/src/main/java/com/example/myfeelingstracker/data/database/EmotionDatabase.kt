package com.example.myfeelingstracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Room database for storing emotion logs.
 */
@Database(entities = [EmotionLog::class], version = 1, exportSchema = false)
abstract class EmotionDatabase : RoomDatabase() {
    abstract fun emotionDao(): EmotionDao

    companion object {
        @Volatile
        private var INSTANCE: EmotionDatabase? = null

        fun getDatabase(context: Context): EmotionDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmotionDatabase::class.java,
                    "emotion_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
