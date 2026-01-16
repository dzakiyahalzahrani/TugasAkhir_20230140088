package com.example.tugasakhir_20230140088.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir_20230140088.R
import com.example.tugasakhir_20230140088.data.repository.FocusRepository
import com.example.tugasakhir_20230140088.utils.AmbiencePlayer
import com.example.tugasakhir_20230140088.utils.NotificationHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FocusViewModel(
    private val repository: FocusRepository,
    private val appContext: Context
) : ViewModel() {

    private var timerJob: Job? = null

    private val _remainingSeconds = MutableStateFlow(0)
    val remainingSeconds: StateFlow<Int> = _remainingSeconds

    private val _isMuted = MutableStateFlow(false)
    val isMuted: StateFlow<Boolean> = _isMuted

    private val _isTimerComplete = MutableStateFlow(false)
    val isTimerComplete: StateFlow<Boolean> = _isTimerComplete

    private val _completedMinutes = MutableStateFlow(0)
    val completedMinutes: StateFlow<Int> = _completedMinutes

    private val _currentAmbience = MutableStateFlow("Rain")
    val currentAmbience: StateFlow<String> = _currentAmbience

    private var startTime: Long = 0L
    private var durationMinutes: Int = 0
    private var selectedAmbience: String = "Rain"

    /* ================= START ================= */

    fun startFocus(duration: Int, ambience: String) {
        durationMinutes = duration
        selectedAmbience = ambience
        _currentAmbience.value = ambience

        startTime = System.currentTimeMillis()

        _isTimerComplete.value = false
        _completedMinutes.value = 0
        _remainingSeconds.value = duration * 60

        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_remainingSeconds.value > 0) {
                delay(1000)
                _remainingSeconds.value--
            }
            onTimerComplete(appContext)
        }
    }

    private fun onTimerComplete(context: Context) {
        stopAmbience()
        _completedMinutes.value = durationMinutes
        _isTimerComplete.value = true

        NotificationHelper.showTimerFinishedNotification(context)
    }

    /* ================= SAVE COMPLETED ================= */

    fun saveCompletedSession(userId: Int) {
        val endedAt = System.currentTimeMillis()

        viewModelScope.launch {
            repository.saveSession(
                userId = userId,
                durationMinutes = durationMinutes,
                ambience = selectedAmbience,
                status = "COMPLETED",
                startedAt = startTime,
                endedAt = endedAt
            )
            _isTimerComplete.value = false
        }
    }

    /* ================= GIVE UP ================= */

    fun giveUp(userId: Int) {
        timerJob?.cancel()
        stopAmbience()

        val endedAt = System.currentTimeMillis()

        val elapsedMinutes =
            ((endedAt - startTime) / 1000 / 60)
                .toInt()
                .coerceAtLeast(1)

        viewModelScope.launch {
            repository.saveSession(
                userId = userId,
                durationMinutes = elapsedMinutes,
                ambience = selectedAmbience,
                status = "STOPPED",
                startedAt = startTime,
                endedAt = endedAt
            )
        }

        _remainingSeconds.value = 0
        _isTimerComplete.value = false
        _completedMinutes.value = 0
    }

    /* ================= AMBIENCE ================= */

    fun playAmbience(context: Context) {
        if (_isMuted.value) return

        val soundRes = when (selectedAmbience) {
            "Rain" -> R.raw.rain
            "Brown" -> R.raw.brown
            "Guitar" -> R.raw.guitar
            "Water" -> R.raw.water
            else -> R.raw.rain
        }

        AmbiencePlayer.start(context, soundRes)
    }

    fun stopAmbience() {
        AmbiencePlayer.stop()
    }

    fun toggleMute(context: Context) {
        _isMuted.value = !_isMuted.value
        if (_isMuted.value) {
            stopAmbience()
        } else {
            playAmbience(context)
        }
    }

    override fun onCleared() {
        stopAmbience()
        timerJob?.cancel()
    }
}
