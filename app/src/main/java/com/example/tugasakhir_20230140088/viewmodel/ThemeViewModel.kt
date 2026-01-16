package com.example.tugasakhir_20230140088.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasakhir_20230140088.ui.theme.AppTheme
import com.example.tugasakhir_20230140088.utils.ThemePreference
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val pref: ThemePreference
) : ViewModel() {

    val currentTheme = pref.themeFlow
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AppTheme.COZY)

    fun setTheme(theme: AppTheme) {
        viewModelScope.launch {
            pref.saveTheme(theme)
        }
    }
}