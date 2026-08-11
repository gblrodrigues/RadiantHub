package com.gblrod.radianthub.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalResources
import androidx.core.splashscreen.SplashScreen
import com.gblrod.radianthub.core.manager.LanguageManager
import com.gblrod.radianthub.ui.language.viewmodel.LanguageViewModel
import com.gblrod.radianthub.ui.theme.ThemeConfigDefault
import com.gblrod.radianthub.ui.theme.viewmodel.ThemeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RadiantHubApp(
    splashScreen: SplashScreen
) {
    val themeViewModel: ThemeViewModel = koinViewModel()
    val languageViewModel: LanguageViewModel = koinViewModel()

    val theme by themeViewModel.theme.collectAsState()
    val language by languageViewModel.language.collectAsState()

    val baseContext = LocalContext.current
    val locale = remember(language) { LanguageManager.resolveLocale(language) }

    val localizedContext = remember(baseContext, locale) {
        LanguageManager.applyLocale(
            base = baseContext,
            locale = locale
        )
    }

    val configuration = localizedContext.resources.configuration

    splashScreen.setKeepOnScreenCondition {
        theme == null
    }

    if (theme != null) {
        CompositionLocalProvider(
            LocalContext provides localizedContext,
            LocalConfiguration provides configuration,
            LocalResources provides localizedContext.resources
        ) {
            ThemeConfigDefault(
                themeOption = theme!!
            ) {
                RadiantHubContent()
            }
        }
    }
}