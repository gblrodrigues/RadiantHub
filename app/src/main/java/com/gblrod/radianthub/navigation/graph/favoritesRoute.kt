package com.gblrod.radianthub.navigation.graph

import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.navigation.extensions.navigateToBottomBar
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
import com.gblrod.radianthub.ui.features.favorites.screen.FavoritesScreen
import com.gblrod.radianthub.ui.features.favorites.viewmodel.FavoritesViewModel

fun NavGraphBuilder.favoritesRoute(
    favoritesViewModel: FavoritesViewModel,
    navHostController: NavHostController,
    agentsViewModel: AgentsViewModel,
    snackbarHostState: SnackbarHostState
) {
    composable(route = Routes.Favorites.route) {
        FavoritesScreen(
            favoritesViewModel = favoritesViewModel,
            agentsViewModel = agentsViewModel,
            onNavigateAgents = {
                navHostController.navigateToBottomBar(route = Routes.Agents.route)
            },
            snackbarHostState = snackbarHostState
        )
    }
}