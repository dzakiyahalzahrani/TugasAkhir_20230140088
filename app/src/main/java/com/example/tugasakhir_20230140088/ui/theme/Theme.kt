package com.example.tugasakhir_20230140088.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/* ===== COZY THEME (Default - Mirip Login Screen) ===== */
private val CozyColorScheme = lightColorScheme(
    // Primary - untuk button utama & highlights
    primary = Color(0xFF6B8E6F),          // Soft green (seperti login button)
    onPrimary = Color.White,

    primaryContainer = Color(0xFFE8F5E9), // Soft green container (card durasi)
    onPrimaryContainer = Color(0xFF1B5E20),

    // Secondary - untuk ambience cards & accents
    secondary = Color(0xFF8B7D6B),        // Warm brown
    onSecondary = Color.White,

    secondaryContainer = Color(0xFFE8DDD2), // Soft beige
    onSecondaryContainer = Color(0xFF3D3832),

    // Background & Surface
    background = Color(0xFFF5F1E8),       // Cream background (sama dengan login)
    onBackground = Color(0xFF3D3832),

    surface = Color.White,                 // Card background
    onSurface = Color(0xFF3D3832),

    surfaceVariant = Color(0xFFFAF8F5),   // Subtle variant
    onSurfaceVariant = Color(0xFF6B5D54),

    // Borders & Outlines
    outline = Color(0xFFD4CCC1),
    outlineVariant = Color(0xFFE8E2D9),

    // Error colors
    error = Color(0xFFD32F2F),
    errorContainer = Color(0xFFFFE5E5),
    onErrorContainer = Color(0xFF8B0000)
)

/* ===== NIGHT THEME (Dark & Cozy) ===== */
private val NightColorScheme = darkColorScheme(
    // Primary - lebih subdued di dark mode
    primary = Color(0xFFB0A89F),          // Soft warm beige
    onPrimary = Color(0xFF2B1B16),

    primaryContainer = Color(0xFF3D322A), // Dark brown container
    onPrimaryContainer = Color(0xFFEADDD7),

    // Secondary
    secondary = Color(0xFFA1887F),        // Muted brown
    onSecondary = Color(0xFF2B1B16),

    secondaryContainer = Color(0xFF4A3732),
    onSecondaryContainer = Color(0xFFE0D0C8),

    // Background & Surface
    background = Color(0xFF1A1614),       // Dark warm brown (bukan hitam murni)
    onBackground = Color(0xFFEDE6E3),

    surface = Color(0xFF252220),          // Card surface sedikit lebih terang
    onSurface = Color(0xFFEDE6E3),

    surfaceVariant = Color(0xFF2D2825),
    onSurfaceVariant = Color(0xFFD0C5BD),

    // Borders & Outlines
    outline = Color(0xFF6B5D54),
    outlineVariant = Color(0xFF4A3F39),

    // Error colors
    error = Color(0xFFFF6B6B),
    errorContainer = Color(0xFF5C2020),
    onErrorContainer = Color(0xFFFFDAD6)
)

/* ===== FOREST THEME (Fresh & Calm) ===== */
private val ForestColorScheme = lightColorScheme(
    // Primary - green tones
    primary = Color(0xFF4F8F6A),          // Medium green
    onPrimary = Color.White,

    primaryContainer = Color(0xFFB7DDBA), // Soft mint green
    onPrimaryContainer = Color(0xFF1B3A1E),

    // Secondary - natural brown accents
    secondary = Color(0xFF7A9B7E),        // Sage green
    onSecondary = Color.White,

    secondaryContainer = Color(0xFFDCEFE2), // Very soft green
    onSecondaryContainer = Color(0xFF1F3D2B),

    // Background & Surface
    background = Color(0xFFF0F7F2),       // Soft greenish white
    onBackground = Color(0xFF1C2B22),

    surface = Color(0xFFFAFDFB),          // Almost white with green tint
    onSurface = Color(0xFF1C2B22),

    surfaceVariant = Color(0xFFE8F5EA),
    onSurfaceVariant = Color(0xFF3D5246),

    // Borders & Outlines
    outline = Color(0xFF9DBF9F),
    outlineVariant = Color(0xFFD0E5D2),

    // Error colors
    error = Color(0xFFD32F2F),
    errorContainer = Color(0xFFFFE5E5),
    onErrorContainer = Color(0xFF8B0000)
)

@Composable
fun TugasAkhir_20230140088Theme(
    appTheme: AppTheme,
    content: @Composable () -> Unit
) {
    val colorScheme = when (appTheme) {
        AppTheme.COZY -> CozyColorScheme
        AppTheme.NIGHT -> NightColorScheme
        AppTheme.FOREST -> ForestColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
