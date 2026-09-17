package com.example.fotoprompts.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColorScheme = darkColorScheme(
    primary = AppPrimary,
    onPrimary = Color.White,
    secondary = AppSecondary,
    onSecondary = Color.White,
    tertiary = AppSecondary,
    background = AppBackground,
    onBackground = AppOnDark,
    surface = AppSurface,
    onSurface = AppOnDark,
    surfaceVariant = AppSurfaceElevated,
    onSurfaceVariant = AppOnDarkMuted,
    surfaceContainer = AppSurface,
    surfaceContainerHigh = AppSurfaceElevated,
    surfaceContainerHighest = AppSurfaceElevated
)

@Composable
fun FotoPromptsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}
