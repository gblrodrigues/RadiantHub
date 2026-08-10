package com.gblrod.radianthub.ui.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gblrod.radianthub.navigation.NavigationGraph
import com.gblrod.radianthub.navigation.state.mapRouteToNavigationUiState
import com.gblrod.radianthub.ui.connectivity.ConnectivitySnackbar
import com.gblrod.radianthub.ui.connectivity.NetworkSnackbarHost
import com.gblrod.radianthub.ui.shared.components.bottombar.RadiantHubBottomBar
import com.gblrod.radianthub.ui.shared.components.topbar.RadiantHubTopBar
import com.gblrod.radianthub.ui.shared.components.RadiantBackground
import com.gblrod.radianthub.ui.shared.components.drawer.DrawerContent
import kotlinx.coroutines.launch

@Composable
fun RadiantHubContent() {
    val navHostController = rememberNavController()
    val scope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }

    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationUiState = mapRouteToNavigationUiState(route = currentRoute)

    ConnectivitySnackbar(
        snackbarHostState = snackbarHostState
    )

    RadiantBackground {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    DrawerContent(
                        navHostController = navHostController,
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
                    if (navigationUiState.showTopBar) {
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
                    }
                },
                snackbarHost = {
                    NetworkSnackbarHost(
                        snackbarHostState = snackbarHostState
                    )
                },
                bottomBar = {
                    if (navigationUiState.showBottomBar) {
                        RadiantHubBottomBar(navHostController = navHostController)
                    }
                }
            ) { paddingValues ->
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavigationGraph(
                        navHostController = navHostController,
                        paddingValues = paddingValues,
                        snackbarHostState = snackbarHostState
                    )
                }
            }
        }
    }
}