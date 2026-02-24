package io.github.dimmddr.myfeelingstracker.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import io.github.dimmddr.myfeelingstracker.data.model.EmotionCategory
import io.github.dimmddr.myfeelingstracker.data.model.IntensityLevel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

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
        assertThat(retrieved?.emotionCategory).isEqualTo(emotion.emotionCategory)
        assertThat(retrieved?.intensityLevel).isEqualTo(emotion.intensityLevel)
        assertThat(retrieved?.timestamp).isEqualTo(emotion.timestamp)
    }

    @Test
    fun getAllEmotionsReturnsEmptyListInitially() = runTest {
        val emotions = dao.getAllEmotions().first()
        assertThat(emotions).isEmpty()
    }

    @Test
    fun getAllEmotionsReturnsSortedByTimestampDesc() = runTest {
        val emotionsToInsert = listOf(
            EmotionLog(
                emotionCategory = EmotionCategory.JOY,
                intensityLevel = IntensityLevel.MILD,
                timestamp = 1000L
            ),
            EmotionLog(
                emotionCategory = EmotionCategory.SADNESS,
                intensityLevel = IntensityLevel.MODERATE,
                timestamp = 2000L
            ),
            EmotionLog(
                emotionCategory = EmotionCategory.ANGER,
                intensityLevel = IntensityLevel.INTENSE,
                timestamp = 1500L
            )
        )
        emotionsToInsert.forEach { dao.insertEmotion(it) }

        val retrieved = dao.getAllEmotions().first()
        val expectedTimestamps = emotionsToInsert.map { it.timestamp }.sortedDescending()

        assertThat(retrieved).hasSize(emotionsToInsert.size)
        assertThat(retrieved.map { it.timestamp }).isEqualTo(expectedTimestamps)
    }

    @Test
    fun updateEmotionModifiesExistingEntry() = runTest {
        val original = EmotionLog(
            emotionCategory = EmotionCategory.FEAR,
            intensityLevel = IntensityLevel.MILD,
            timestamp = 1000L
        )

        val id = dao.insertEmotion(original)
        val updated = dao.getEmotionById(id)!!.copy(
            intensityLevel = IntensityLevel.INTENSE,
            lastModified = 2000L
        )
        dao.updateEmotion(updated)

        val final = dao.getEmotionById(id)
        assertThat(final?.intensityLevel).isEqualTo(updated.intensityLevel)
        assertThat(final?.lastModified).isEqualTo(updated.lastModified)
    }

    @Test
    fun deleteEmotionRemovesEntry() = runTest {
        val emotion = EmotionLog(
            emotionCategory = EmotionCategory.TRUST,
            intensityLevel = IntensityLevel.MODERATE,
            timestamp = 1000L
        )

        val id = dao.insertEmotion(emotion)
        dao.deleteEmotion(dao.getEmotionById(id)!!)

        assertThat(dao.getEmotionById(id)).isNull()
    }

    @Test
    fun deleteAllEmotionsClearsDatabase() = runTest {
        listOf(
            EmotionLog(
                emotionCategory = EmotionCategory.JOY,
                intensityLevel = IntensityLevel.MILD,
                timestamp = 1000L
            ),
            EmotionLog(
                emotionCategory = EmotionCategory.SADNESS,
                intensityLevel = IntensityLevel.MODERATE,
                timestamp = 2000L
            )
        ).forEach { dao.insertEmotion(it) }

        dao.deleteAllEmotions()

        assertThat(dao.getAllEmotions().first()).isEmpty()
    }
}
