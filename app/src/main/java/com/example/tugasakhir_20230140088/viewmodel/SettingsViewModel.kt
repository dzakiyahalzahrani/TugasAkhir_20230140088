package com.example.tugasakhir_20230140088.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir_20230140088.data.entity.UserSettingsEntity
import com.example.tugasakhir_20230140088.data.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: SettingsRepository
) : ViewModel() {

    private val currentUserId = 1

    private val _userSettings = MutableStateFlow<UserSettingsEntity?>(null)
    val userSettings: StateFlow<UserSettingsEntity?> = _userSettings

    private val _currentTheme = MutableStateFlow("Cozy")
    val currentTheme: StateFlow<String> = _currentTheme

    init {
        observeUserSettings()
    }

    private fun observeUserSettings() {
        viewModelScope.launch {
            repository.getUserSettings(currentUserId).collectLatest { settings ->
                if (settings == null) {
                    repository.initDefaultSettings(currentUserId)
                } else {
                    _userSettings.value = settings
                    _currentTheme.value = settings.themeName
                }
            }
        }
    }

    fun updateTheme(themeName: String) {
        viewModelScope.launch {
            repository.updateTheme(currentUserId, themeName)
        }
    }

    fun logout(onLogoutSuccess: () -> Unit) {
        onLogoutSuccess()
    }
}
