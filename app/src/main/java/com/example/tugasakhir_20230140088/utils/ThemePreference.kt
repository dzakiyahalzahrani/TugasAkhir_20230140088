package com.example.tugasakhir_20230140088.utils

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.tugasakhir_20230140088.ui.theme.AppTheme
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

class ThemePreference(private val context: Context) {

    companion object {
        private val THEME_KEY = stringPreferencesKey("app_theme")
    }

    val themeFlow = context.dataStore.data.map {
        AppTheme.valueOf(it[THEME_KEY] ?: AppTheme.COZY.name)
    }

    suspend fun saveTheme(theme: AppTheme) {
        context.dataStore.edit {
            it[THEME_KEY] = theme.name
        }
    }
}