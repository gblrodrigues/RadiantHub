package com.gblrod.radianthub.di

import com.gblrod.radianthub.data.preferences.datastore.dataStore
import com.gblrod.radianthub.data.preferences.repository.UserPreferencesRepository
import com.gblrod.radianthub.ui.theme.viewmodel.ThemeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val preferencesModule = module {

    // DataStore
    single { androidContext().dataStore }

    singleOf(constructor = ::UserPreferencesRepository)

    // viewModel
    viewModelOf(constructor = ::ThemeViewModel)
}