package com.uag.augk12.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme  = lightColorScheme(
    primary = Red80,
    onPrimary = black,
    secondary = Yellow80,
    onSecondary = black,
    tertiary = Blue80,
    onTertiary = white,
    background = white,
    onBackground = black,
    surface = white,
    onSurface = black
)

@Composable
fun AUGK12Theme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColorScheme,
        typography = Typography,
        content = content
    )
}