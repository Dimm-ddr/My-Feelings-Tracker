package com.example.myfeelingstracker.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfeelingstracker.data.database.EmotionDatabase
import com.example.myfeelingstracker.data.database.EmotionLog
import com.example.myfeelingstracker.data.repository.EmotionRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * Main ViewModel following MVVM pattern.
 * Manages UI-related data and business logic.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: EmotionRepository
    
    val allEmotions: StateFlow<List<EmotionLog>>

    init {
        val emotionDao = EmotionDatabase.getDatabase(application).emotionDao()
        repository = EmotionRepository(emotionDao)
        
        allEmotions = repository.allEmotions.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    fun insertEmotion(emotion: EmotionLog) {
        viewModelScope.launch {
            repository.insertEmotion(emotion)
        }
    }

    fun updateEmotion(emotion: EmotionLog) {
        viewModelScope.launch {
            repository.updateEmotion(emotion)
        }
    }

    fun deleteEmotion(emotion: EmotionLog) {
        viewModelScope.launch {
            repository.deleteEmotion(emotion)
        }
    }
}
