package com.gblrod.radianthub.core.utils

import com.gblrod.radianthub.ui.language.LanguageOptions
import java.util.Locale

fun getDeviceLanguage(): LanguageOptions {
    return when (Locale.getDefault().language) {
        "pt" -> LanguageOptions.PT_BR
        "es" -> LanguageOptions.ES
        else -> LanguageOptions.EN_US
    }
}

fun LanguageOptions?.orDeviceDefault(): LanguageOptions {
    return this ?: getDeviceLanguage()
}