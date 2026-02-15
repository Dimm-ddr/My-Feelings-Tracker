package com.example.myfeelingstracker.data.repository

import com.example.myfeelingstracker.data.database.EmotionDao
import com.example.myfeelingstracker.data.database.EmotionLog
import kotlinx.coroutines.flow.Flow

/**
 * Repository for managing emotion data.
 * Provides a clean API for data access to the rest of the application.
 */
class EmotionRepository(private val emotionDao: EmotionDao) {

    val allEmotions: Flow<List<EmotionLog>> = emotionDao.getAllEmotions()

    suspend fun getEmotionById(id: Long): EmotionLog? {
        return emotionDao.getEmotionById(id)
    }

    suspend fun insertEmotion(emotion: EmotionLog): Long {
        return emotionDao.insertEmotion(emotion)
    }

    suspend fun updateEmotion(emotion: EmotionLog) {
        emotionDao.updateEmotion(emotion)
    }

    suspend fun deleteEmotion(emotion: EmotionLog) {
        emotionDao.deleteEmotion(emotion)
    }

    suspend fun deleteAllEmotions() {
        emotionDao.deleteAllEmotions()
    }
}
