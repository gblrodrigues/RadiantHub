package com.gblrod.radianthub.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gblrod.radianthub.core.manager.LanguageManager
import com.gblrod.radianthub.ui.app.RadiantHubApp

class MainActivity : ComponentActivity() {
    override fun attachBaseContext(newBase: Context) {
        val language = LanguageManager.getStoredLanguage(newBase)
        val locale = LanguageManager.resolveLocale(language)
        val context = LanguageManager.applyLocale(newBase, locale)
        super.attachBaseContext(context)
    }

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.isNavigationBarContrastEnforced = false

        val splashScreen = installSplashScreen()
        setContent {
            RadiantHubApp(
                splashScreen = splashScreen
            )
        }
    }
}