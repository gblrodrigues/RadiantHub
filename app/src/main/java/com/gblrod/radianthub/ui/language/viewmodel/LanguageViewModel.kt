package com.gblrod.radianthub.ui.language.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gblrod.radianthub.data.preferences.repository.UserPreferencesRepository
import com.gblrod.radianthub.ui.language.LanguageOptions
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class LanguageViewModel(
    private val repository: UserPreferencesRepository
) : ViewModel() {
    val language = repository.userPreferences
        .map { it.language }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = null
        )

    suspend fun setLanguage(language: LanguageOptions) {
        repository.saveLanguage(language)
    }
}