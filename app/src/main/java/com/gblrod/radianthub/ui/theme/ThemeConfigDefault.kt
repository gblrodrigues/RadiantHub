package com.gblrod.radianthub.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun ThemeConfigDefault(
    themeOption: ThemeOptions,
    content: @Composable () -> Unit
) {
    val isDarkMode = when (themeOption) {
        ThemeOptions.DARK -> true
        ThemeOptions.LIGHT -> false
        ThemeOptions.SYSTEM -> isSystemInDarkTheme()
    }

    val view = LocalView.current

    SideEffect {
        val window = (view.context as Activity).window
        val controller = WindowCompat.getInsetsController(window, view)

        controller.isAppearanceLightStatusBars = !isDarkMode
        controller.isAppearanceLightNavigationBars = !isDarkMode
    }

    MaterialTheme(
        colorScheme = if (isDarkMode) darkColorScheme() else lightColorScheme(),
        content = content
    )
}