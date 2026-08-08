package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.search.screen.SearchScreen

fun NavGraphBuilder.searchRoute(navHostController: NavHostController) {
    composable(route = Routes.Search.ROUTE) {
        SearchScreen(navHostController = navHostController,)
    }
}