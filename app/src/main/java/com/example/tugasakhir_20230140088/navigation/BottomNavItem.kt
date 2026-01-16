package com.example.tugasakhir_20230140088.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.tugasakhir_20230140088.navigation.NavRoutes

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    object Focus : BottomNavItem(
        route = NavRoutes.FOCUS,
        label = "Focus",
        icon = Icons.Default.PlayArrow
    )

    object Templates : BottomNavItem(
        route = NavRoutes.TEMPLATES,
        label = "Templates",
        icon = Icons.Default.Edit
    )

    object Stats : BottomNavItem(
        route = NavRoutes.STATISTICS,
        label = "Stats",
        icon = Icons.Default.BarChart
    )

    object Settings : BottomNavItem(
        route = NavRoutes.SETTINGS,
        label = "Settings",
        icon = Icons.Default.Settings
    )
}
