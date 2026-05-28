package com.gblrod.radianthub.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gblrod.radianthub.navigation.NavigationGraph
import com.gblrod.radianthub.navigation.state.mapRouteToNavigationUiState
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
import com.gblrod.radianthub.ui.features.favorites.viewmodel.FavoritesViewModel
import com.gblrod.radianthub.ui.features.maps.viewmodel.MapsViewModel
import com.gblrod.radianthub.ui.shared.components.RadiantBackground
import com.gblrod.radianthub.ui.shared.components.bottombar.RadiantHubBottomBar
import com.gblrod.radianthub.ui.shared.components.drawer.DrawerContent
import com.gblrod.radianthub.ui.shared.components.topbar.RadiantHubTopBar
import com.gblrod.radianthub.ui.theme.ThemeConfigDefault
import com.gblrod.radianthub.ui.theme.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.isNavigationBarContrastEnforced = false

        val splashScreen = installSplashScreen()
        setContent {
            val agentsViewModel: AgentsViewModel = koinViewModel()
            val themeViewModel: ThemeViewModel = koinViewModel()
            val mapsViewModel: MapsViewModel = koinViewModel()
            val favoritesViewModel: FavoritesViewModel = koinViewModel()

            val navHostController = rememberNavController()
            val scope = rememberCoroutineScope()
            val theme by themeViewModel.theme.collectAsState()

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val snackbarHostState = remember { SnackbarHostState() }

            val navBackStackEntry by navHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            val navigationUiState = mapRouteToNavigationUiState(route = currentRoute)

            splashScreen.setKeepOnScreenCondition {
                theme == null
            }

            if (theme != null) {
                ThemeConfigDefault(
                    themeOption = theme!!
                ) {
                    RadiantBackground {
                        ModalNavigationDrawer(
                            drawerState = drawerState,
                            drawerContent = {
                                ModalDrawerSheet {
                                    DrawerContent(
                                        navController = navHostController,
                                        themeViewModel = themeViewModel,
                                        onItemClick = {
                                            scope.launch {
                                                drawerState.close()
                                            }
                                        }
                                    )
                                }
                            }
                        ) {
                            Scaffold(
                                modifier = Modifier.fillMaxSize(),
                                containerColor = Color.Transparent,
                                topBar = {
                                    RadiantHubTopBar(
                                        onOpenDrawer = {
                                            scope.launch {
                                                if (drawerState.isClosed) drawerState.open()
                                                else drawerState.close()
                                            }
                                        },
                                        navHostController = navHostController,
                                        navigationUiState = navigationUiState
                                    )
                                },
                                snackbarHost = {
                                    SnackbarHost(
                                        hostState = snackbarHostState
                                    )
                                },
                                bottomBar = {
                                    RadiantHubBottomBar(navHostController = navHostController)
                                }
                            ) { paddingValues ->
                                Box(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    NavigationGraph(
                                        agentsViewModel = agentsViewModel,
                                        mapsViewModel = mapsViewModel,
                                        favoritesViewModel = favoritesViewModel,
                                        navHostController = navHostController,
                                        paddingValues = paddingValues,
                                        snackbarHostState = snackbarHostState
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}