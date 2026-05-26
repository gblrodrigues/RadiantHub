package com.gblrod.radianthub.data.preferences.model

import com.gblrod.radianthub.ui.theme.ThemeOptions

data class UserPreferences(
    val theme: ThemeOptions = ThemeOptions.SYSTEM,
    // val language: LanguageOptions? = null,
)