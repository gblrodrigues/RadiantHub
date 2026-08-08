package com.gblrod.radianthub.ui.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen
import com.gblrod.radianthub.ui.theme.ThemeConfigDefault
import com.gblrod.radianthub.ui.theme.viewmodel.ThemeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RadiantHubApp(
    splashScreen: SplashScreen
) {
    val themeViewModel: ThemeViewModel = koinViewModel()
    val theme by themeViewModel.theme.collectAsState()

    splashScreen.setKeepOnScreenCondition {
        theme == null
    }

    if (theme != null) {
        ThemeConfigDefault(
            themeOption = theme!!
        ) {
            RadiantHubContent()
        }
    }
}