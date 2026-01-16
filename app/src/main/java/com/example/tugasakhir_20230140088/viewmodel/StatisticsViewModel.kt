package com.example.tugasakhir_20230140088.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir_20230140088.data.entity.FocusSessionEntity
import com.example.tugasakhir_20230140088.data.repository.StatisticsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class StatisticsViewModel(
    private val repository: StatisticsRepository
) : ViewModel() {

    private val _totalMinutes = MutableStateFlow(0)
    val totalMinutes: StateFlow<Int> = _totalMinutes

    private val _totalSessions = MutableStateFlow(0)
    val totalSessions: StateFlow<Int> = _totalSessions

    private val _sessions =
        MutableStateFlow<List<FocusSessionEntity>>(emptyList())
    val sessions: StateFlow<List<FocusSessionEntity>> = _sessions

    fun loadStatistics(date: LocalDate) {
        val dateString = date.toString() // yyyy-MM-dd

        viewModelScope.launch {
            val sessions = repository.getSessionsByDate(dateString)

            _sessions.value = sessions
            _totalSessions.value = sessions.size
            _totalMinutes.value = sessions.sumOf { it.durationMinutes }
        }
    }
}

