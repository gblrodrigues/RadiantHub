package com.gblrod.radianthub.data.preferences.datastore

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "user_preferences")