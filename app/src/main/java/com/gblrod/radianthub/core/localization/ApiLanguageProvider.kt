package com.gblrod.radianthub.core.localization

import android.content.Context
import com.gblrod.radianthub.core.extensions.toApiLanguage
import com.gblrod.radianthub.core.manager.LanguageManager
import com.gblrod.radianthub.core.utils.orDeviceDefault

class ApiLanguageProvider(
    private val context: Context
) {
    fun getLanguage(): String {
        val language = LanguageManager
            .getStoredLanguage(context)
            .orDeviceDefault()
        return language.toApiLanguage()
    }
}