package com.gblrod.radianthub.core.manager

import android.content.Context
import android.content.res.Configuration
import com.gblrod.radianthub.core.utils.getDeviceLanguage
import com.gblrod.radianthub.ui.language.LanguageOptions
import java.util.Locale

object LanguageManager {
    fun resolveLocale(language: LanguageOptions?): Locale {
        return when (language ?: getDeviceLanguage()) {
            LanguageOptions.PT_BR -> Locale.forLanguageTag("pt-BR")
            LanguageOptions.EN_US -> Locale.forLanguageTag("en")
            LanguageOptions.ES -> Locale.forLanguageTag("es")
        }
    }

    fun applyLocale(base: Context, locale: Locale): Context {
        val config = Configuration(base.resources.configuration)
        config.setLocale(locale)
        return base.createConfigurationContext(config)
    }
}