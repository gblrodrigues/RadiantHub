package com.gblrod.radianthub.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gblrod.radianthub.data.preferences.repository.UserPreferencesRepository
import com.gblrod.radianthub.ui.theme.ThemeOptions
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(
    private val repository: UserPreferencesRepository
) : ViewModel(){
    val theme = repository.userPreferences
        .map { it.theme }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = null
        )

    fun setTheme(
        theme: ThemeOptions
    ) {
        viewModelScope.launch {
            repository.saveTheme(theme)
        }
    }
}