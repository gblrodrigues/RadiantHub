package com.gblrod.radianthub.ui.theme

import com.gblrod.radianthub.R

enum class ThemeOptions(
    val label: Int
) {
    DARK(label = R.string.theme_darkmode),
    LIGHT(label = R.string.theme_lightmode),
    SYSTEM(label = R.string.theme_system)
}