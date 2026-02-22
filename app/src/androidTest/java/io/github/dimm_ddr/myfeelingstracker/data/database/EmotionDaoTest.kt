package io.github.dimm_ddr.myfeelingstracker.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.dimm_ddr.myfeelingstracker.data.model.EmotionCategory
import io.github.dimm_ddr.myfeelingstracker.data.model.IntensityLevel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests for EmotionDao.
 * Tests database operations with an in-memory Room database.
 */
@RunWith(AndroidJUnit4::class)
class EmotionDaoTest {

    private lateinit var database: EmotionDatabase
    private lateinit var dao: EmotionDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            EmotionDatabase::class.java
        ).build()
        dao = database.emotionDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertAndGetEmotion() = runTest {
        val emotion = EmotionLog(
            emotionCategory = EmotionCategory.JOY,
            intensityLevel = IntensityLevel.MODERATE,
            timestamp = 1000L
        )

        val id = dao.insertEmotion(emotion)
        val retrieved = dao.getEmotionById(id)

        assertThat(retrieved).isNotNull()
        assertThat(retrieved?.emotionCategory).isEqualTo(EmotionCategory.JOY)
        assertThat(retrieved?.intensityLevel).isEqualTo(IntensityLevel.MODERATE)
    }

    @Test
    fun getAllEmotionsReturnsEmptyListInitially() = runTest {
        val emotions = dao.getAllEmotions().first()
        assertThat(emotions).isEmpty()
    }

    @Test
    fun getAllEmotionsReturnsSortedByTimestampDesc() = runTest {
        val emotion1 = EmotionLog(
            emotionCategory = EmotionCategory.JOY,
            intensityLevel = IntensityLevel.MILD,
            timestamp = 1000L
        )
        val emotion2 = EmotionLog(
            emotionCategory = EmotionCategory.SADNESS,
            intensityLevel = IntensityLevel.MODERATE,
            timestamp = 2000L
        )
        val emotion3 = EmotionLog(
            emotionCategory = EmotionCategory.ANGER,
            intensityLevel = IntensityLevel.INTENSE,
            timestamp = 1500L
        )

        dao.insertEmotion(emotion1)
        dao.insertEmotion(emotion2)
        dao.insertEmotion(emotion3)

        val emotions = dao.getAllEmotions().first()
        
        assertThat(emotions).hasSize(3)
        assertThat(emotions[0].timestamp).isEqualTo(2000L) // Most recent first
        assertThat(emotions[1].timestamp).isEqualTo(1500L)
        assertThat(emotions[2].timestamp).isEqualTo(1000L)
    }

    @Test
    fun updateEmotionModifiesExistingEntry() = runTest {
        val original = EmotionLog(
            emotionCategory = EmotionCategory.FEAR,
            intensityLevel = IntensityLevel.MILD,
            timestamp = 1000L,
            lastModified = null
        )

        val id = dao.insertEmotion(original)
        val retrieved = dao.getEmotionById(id)!!
        
        val updated = retrieved.copy(
            intensityLevel = IntensityLevel.INTENSE,
            lastModified = 2000L
        )
        dao.updateEmotion(updated)

        val final = dao.getEmotionById(id)
        assertThat(final?.intensityLevel).isEqualTo(IntensityLevel.INTENSE)
        assertThat(final?.lastModified).isEqualTo(2000L)
    }

    @Test
    fun deleteEmotionRemovesEntry() = runTest {
        val emotion = EmotionLog(
            emotionCategory = EmotionCategory.TRUST,
            intensityLevel = IntensityLevel.MODERATE,
            timestamp = 1000L
        )

        val id = dao.insertEmotion(emotion)
        val retrieved = dao.getEmotionById(id)!!
        
        dao.deleteEmotion(retrieved)
        
        val deleted = dao.getEmotionById(id)
        assertThat(deleted).isNull()
    }

    @Test
    fun deleteAllEmotionsClearsDatabase() = runTest {
        dao.insertEmotion(EmotionLog(
            emotionCategory = EmotionCategory.JOY,
            intensityLevel = IntensityLevel.MILD,
            timestamp = 1000L
        ))
        dao.insertEmotion(EmotionLog(
            emotionCategory = EmotionCategory.SADNESS,
            intensityLevel = IntensityLevel.MODERATE,
            timestamp = 2000L
        ))

        dao.deleteAllEmotions()
        
        val emotions = dao.getAllEmotions().first()
        assertThat(emotions).isEmpty()
    }

    @Test
    fun insertEmotionWithReplaceStrategyOverwritesConflict() = runTest {
        val emotion1 = EmotionLog(
            id = 1L,
            emotionCategory = EmotionCategory.DISGUST,
            intensityLevel = IntensityLevel.MILD,
            timestamp = 1000L
        )
        val emotion2 = EmotionLog(
            id = 1L,
            emotionCategory = EmotionCategory.SURPRISE,
            intensityLevel = IntensityLevel.INTENSE,
            timestamp = 2000L
        )

        dao.insertEmotion(emotion1)
        dao.insertEmotion(emotion2)

        val emotions = dao.getAllEmotions().first()
        assertThat(emotions).hasSize(1)
        assertThat(emotions[0].emotionCategory).isEqualTo(EmotionCategory.SURPRISE)
    }
}
