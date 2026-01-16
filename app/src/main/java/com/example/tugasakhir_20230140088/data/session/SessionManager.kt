package com.example.tugasakhir_20230140088.data.session

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "session_prefs")

class SessionManager(
    private val context: Context
) {

    companion object {
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }

    // ✅ SATU-SATUNYA SUMBER KEBENARAN
    val isLoggedIn: Flow<Boolean> =
        context.dataStore.data.map { prefs ->
            prefs[IS_LOGGED_IN] ?: false
        }

    suspend fun setLoggedIn(value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = value
        }
    }

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }
}
