package com.example.tugasakhir_20230140088

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.tugasakhir_20230140088.data.database.NoirestDatabase
import com.example.tugasakhir_20230140088.data.repository.AuthRepository
import com.example.tugasakhir_20230140088.data.repository.FocusRepository
import com.example.tugasakhir_20230140088.data.repository.SettingsRepository
import com.example.tugasakhir_20230140088.data.repository.StatisticsRepository
import com.example.tugasakhir_20230140088.data.repository.TemplateRepository
import com.example.tugasakhir_20230140088.data.session.SessionManager
import com.example.tugasakhir_20230140088.navigation.MainNavGraph
import com.example.tugasakhir_20230140088.ui.theme.TugasAkhir_20230140088Theme
import com.example.tugasakhir_20230140088.viewmodel.AuthViewModel
import com.example.tugasakhir_20230140088.viewmodel.FocusViewModel
import com.example.tugasakhir_20230140088.viewmodel.SettingsViewModel
import com.example.tugasakhir_20230140088.viewmodel.StatisticsViewModel
import com.example.tugasakhir_20230140088.viewmodel.TemplateViewModel
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugasakhir_20230140088.utils.ThemePreference
import com.example.tugasakhir_20230140088.viewmodel.ThemeViewModel
import com.example.tugasakhir_20230140088.viewmodel.ThemeViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // === DATABASE ===
        val database = NoirestDatabase.getInstance(applicationContext)

        // === REPOSITORY ===
        val authRepository = AuthRepository(database.userDao())
        val focusRepository = FocusRepository(database.focusSessionDao())
        val statisticsRepository = StatisticsRepository(database.focusSessionDao())
        val settingsRepository = SettingsRepository(database.userSettingsDao())
        val templateRepository = TemplateRepository(database.timerTemplateDao())
        // === SESSION ===
        val sessionManager = SessionManager(applicationContext)
        // === ENSURE DEFAULT USER (WAJIB SEBELUM SETTINGS) ===
        lifecycleScope.launch {
            authRepository.ensureDefaultUser()
        }

        // === VIEWMODEL NON-COMPOSE ===
        val authViewModel =
            AuthViewModel(repository = authRepository, sessionManager = sessionManager)
        val focusViewModel = FocusViewModel(focusRepository, applicationContext)
        val statisticsViewModel = StatisticsViewModel(statisticsRepository)
        val settingsViewModel = SettingsViewModel(settingsRepository)
        val templateViewModel = TemplateViewModel(templateRepository)

        setContent {

            // ✅ THEME VIEWMODEL HARUS DI COMPOSE
            val themeViewModel: ThemeViewModel = viewModel(
                factory = ThemeViewModelFactory(
                    ThemePreference(applicationContext)
                )
            )

            // ✅ collectAsState HANYA DI SINI
            val currentTheme by themeViewModel.currentTheme.collectAsState()

            TugasAkhir_20230140088Theme(
                appTheme = currentTheme
            ) {
                val navController = rememberNavController()

                MainNavGraph(
                    navController = navController,
                    authViewModel = authViewModel,
                    focusViewModel = focusViewModel,
                    statisticsViewModel = statisticsViewModel,
                    settingsViewModel = settingsViewModel,
                    templateViewModel = templateViewModel,
                    themeViewModel = themeViewModel
                )
            }
        }

    }
}

