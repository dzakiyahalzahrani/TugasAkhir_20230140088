package com.example.tugasakhir_20230140088.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tugasakhir_20230140088.ui.auth.AuthScreen
import com.example.tugasakhir_20230140088.ui.auth.RegisterScreen
import com.example.tugasakhir_20230140088.ui.focus.FocusScreen
import com.example.tugasakhir_20230140088.ui.focus.TimerRunningScreen
import com.example.tugasakhir_20230140088.ui.settings.SettingsScreen
import com.example.tugasakhir_20230140088.ui.statistics.StatisticsScreen
import com.example.tugasakhir_20230140088.ui.templates.TemplatesScreen
import com.example.tugasakhir_20230140088.viewmodel.*

@Composable
fun MainNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    focusViewModel: FocusViewModel,
    statisticsViewModel: StatisticsViewModel,
    settingsViewModel: SettingsViewModel,
    templateViewModel: TemplateViewModel,
    themeViewModel: ThemeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.AUTH
    ) {

        composable(NavRoutes.AUTH) {
            AuthScreen(
                viewModel = authViewModel,
                navController = navController
            )
        }

        composable(NavRoutes.REGISTER) {
            RegisterScreen(
                viewModel = authViewModel,
                navController = navController
            )
        }

        composable(NavRoutes.FOCUS) {
            FocusScreen(
                viewModel = focusViewModel,
                templateViewModel = templateViewModel,
                navController = navController
            )
        }

        composable(NavRoutes.STATISTICS) {
            StatisticsScreen(
                viewModel = statisticsViewModel,
                navController = navController  // ⬅️ INI YANG DITAMBAHKAN
            )
        }

        composable("settings") {
            SettingsScreen(
                viewModel = settingsViewModel,
                navController = navController,
                themeViewModel = themeViewModel
            )
        }


        composable(route = NavRoutes.TEMPLATES) {
            TemplatesScreen(
                viewModel = templateViewModel,
                navController = navController
            )
        }

        composable("timer_running") {
            TimerRunningScreen(
                viewModel = focusViewModel,
                navController = navController
            )
        }
    }
}
