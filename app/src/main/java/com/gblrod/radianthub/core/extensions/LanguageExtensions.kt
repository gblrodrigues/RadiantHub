package com.gblrod.radianthub.core.extensions

import com.gblrod.radianthub.ui.language.LanguageOptions

fun LanguageOptions.toApiLanguage(): String {
    return when (this) {
        LanguageOptions.PT_BR -> "pt-BR"
        LanguageOptions.EN_US -> "en-US"
        LanguageOptions.ES -> "es-ES"
    }
}