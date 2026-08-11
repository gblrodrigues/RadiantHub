package com.gblrod.radianthub.core.localization

import com.gblrod.radianthub.core.extensions.toApiLanguage
import com.gblrod.radianthub.core.utils.orDeviceDefault
import com.gblrod.radianthub.data.preferences.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.first

class ApiLanguageProvider(
    private val repository: UserPreferencesRepository
) {
    suspend fun getLanguage(): String {
        val language = repository.userPreferences
            .first()
            .language
            .orDeviceDefault()

        return language.toApiLanguage()
    }
}