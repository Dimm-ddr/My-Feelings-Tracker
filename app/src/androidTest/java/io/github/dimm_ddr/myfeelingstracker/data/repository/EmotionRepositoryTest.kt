package io.github.dimm_ddr.myfeelingstracker.data.repository

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.dimm_ddr.myfeelingstracker.data.database.EmotionDatabase
import io.github.dimm_ddr.myfeelingstracker.data.database.EmotionLog
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
 * Instrumented tests for EmotionRepository.
 * Tests repository operations with an in-memory Room database.
 */
@RunWith(AndroidJUnit4::class)
class EmotionRepositoryTest {

    private lateinit var database: EmotionDatabase
    private lateinit var repository: EmotionRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            EmotionDatabase::class.java
        ).build()
        repository = EmotionRepository(database.emotionDao())
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun repositoryInsertAndRetrieve() = runTest {
        val emotion = EmotionLog(
            emotionCategory = EmotionCategory.ANTICIPATION,
            intensityLevel = IntensityLevel.MILD,
            timestamp = 1000L
        )

        val id = repository.insertEmotion(emotion)
        val retrieved = repository.getEmotionById(id)

        assertThat(retrieved).isNotNull()
        assertThat(retrieved?.emotionCategory).isEqualTo(EmotionCategory.ANTICIPATION)
    }

    @Test
    fun repositoryAllEmotionsFlow() = runTest {
        val emotion1 = EmotionLog(
            emotionCategory = EmotionCategory.JOY,
            intensityLevel = IntensityLevel.MODERATE,
            timestamp = 1000L
        )
        val emotion2 = EmotionLog(
            emotionCategory = EmotionCategory.TRUST,
            intensityLevel = IntensityLevel.INTENSE,
            timestamp = 2000L
        )

        repository.insertEmotion(emotion1)
        repository.insertEmotion(emotion2)

        val emotions = repository.allEmotions.first()
        assertThat(emotions).hasSize(2)
    }

    @Test
    fun repositoryUpdateEmotion() = runTest {
        val original = EmotionLog(
            emotionCategory = EmotionCategory.SURPRISE,
            intensityLevel = IntensityLevel.MILD,
            timestamp = 1000L
        )

        val id = repository.insertEmotion(original)
        val retrieved = repository.getEmotionById(id)!!
        
        val updated = retrieved.copy(
            intensityLevel = IntensityLevel.INTENSE,
            lastModified = 2000L
        )
        repository.updateEmotion(updated)

        val final = repository.getEmotionById(id)
        assertThat(final?.intensityLevel).isEqualTo(IntensityLevel.INTENSE)
    }

    @Test
    fun repositoryDeleteEmotion() = runTest {
        val emotion = EmotionLog(
            emotionCategory = EmotionCategory.DISGUST,
            intensityLevel = IntensityLevel.MODERATE,
            timestamp = 1000L
        )

        val id = repository.insertEmotion(emotion)
        val retrieved = repository.getEmotionById(id)!!
        
        repository.deleteEmotion(retrieved)
        
        val deleted = repository.getEmotionById(id)
        assertThat(deleted).isNull()
    }

    @Test
    fun repositoryDeleteAllEmotions() = runTest {
        repository.insertEmotion(EmotionLog(
            emotionCategory = EmotionCategory.ANGER,
            intensityLevel = IntensityLevel.MILD,
            timestamp = 1000L
        ))
        repository.insertEmotion(EmotionLog(
            emotionCategory = EmotionCategory.FEAR,
            intensityLevel = IntensityLevel.INTENSE,
            timestamp = 2000L
        ))

        repository.deleteAllEmotions()
        
        val emotions = repository.allEmotions.first()
        assertThat(emotions).isEmpty()
    }
}
