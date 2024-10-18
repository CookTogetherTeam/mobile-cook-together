package com.cooktogether.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme =
    darkColorScheme(
        primary = CTColor.Primary.color,
        secondary = CTColor.Dark.color,
        surface = CTColor.Surface.color,
        background = CTColor.Background.color,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = CTColor.Primary.color,
        secondary = CTColor.Dark.color,
        surface = CTColor.Surface.color,
        background = CTColor.Background.color,
    )

@Composable
fun CookTogetherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}