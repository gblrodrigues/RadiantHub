package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.favorites.screen.FavoritesScreen

fun NavGraphBuilder.favoritesRoute() {
    composable(route = Routes.Favorites.route) {
        FavoritesScreen()
    }
}