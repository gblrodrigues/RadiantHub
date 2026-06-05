package com.gblrod.radianthub.data.preferences.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.gblrod.radianthub.data.preferences.model.UserPreferences
import com.gblrod.radianthub.ui.language.LanguageOptions
import com.gblrod.radianthub.ui.theme.ThemeOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val themeKey = stringPreferencesKey(name = "theme")
private val languageKey = stringPreferencesKey(name = "language")

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>
) {
    val userPreferences: Flow<UserPreferences> = dataStore.data.map { prefs ->
        UserPreferences(
            theme = ThemeOptions.entries.find {
                it.name == prefs[themeKey]
            } ?: ThemeOptions.SYSTEM,

            language = prefs[languageKey]?.let { langValue ->
                LanguageOptions.entries.find {
                    it.name == langValue
                }
            }
        )
    }

    suspend fun saveLanguage(language: LanguageOptions) {
        dataStore.edit { prefs ->
            prefs[languageKey] = language.name
        }
    }

    suspend fun saveTheme(theme: ThemeOptions) {
        dataStore.edit { prefs ->
            prefs[themeKey] = theme.name
        }
    }
}