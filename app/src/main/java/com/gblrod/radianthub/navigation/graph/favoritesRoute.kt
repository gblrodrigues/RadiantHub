package com.gblrod.radianthub.navigation.graph

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.navigation.extensions.navigateToBottomBar
import com.gblrod.radianthub.ui.features.favorites.screen.FavoritesScreen

fun NavGraphBuilder.favoritesRoute(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    composable(route = Routes.Favorites.ROUTE) {
        FavoritesScreen(
            onNavigateHome = { navHostController.navigateToBottomBar(route = Routes.Home.ROUTE) },
            snackbarHostState = snackbarHostState
        )
    }
}