package com.example.myfeelingstracker.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for EmotionLog.
 */
@Dao
interface EmotionDao {
    @Query("SELECT * FROM emotion_logs ORDER BY timestamp DESC")
    fun getAllEmotions(): Flow<List<EmotionLog>>

    @Query("SELECT * FROM emotion_logs WHERE id = :id")
    suspend fun getEmotionById(id: Long): EmotionLog?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmotion(emotion: EmotionLog): Long

    @Update
    suspend fun updateEmotion(emotion: EmotionLog)

    @Delete
    suspend fun deleteEmotion(emotion: EmotionLog)

    @Query("DELETE FROM emotion_logs")
    suspend fun deleteAllEmotions()
}
