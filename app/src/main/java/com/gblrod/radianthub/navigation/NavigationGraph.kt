package com.gblrod.radianthub.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gblrod.radianthub.navigation.graph.agentsRoute
import com.gblrod.radianthub.navigation.graph.cardsRoute
import com.gblrod.radianthub.navigation.graph.favoritesRoute
import com.gblrod.radianthub.navigation.graph.homeRoute
import com.gblrod.radianthub.navigation.graph.mapsRoute
import com.gblrod.radianthub.navigation.graph.searchRoute
import com.gblrod.radianthub.navigation.graph.tiersRoute

@Composable
fun NavigationGraph(
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Home.ROUTE,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        homeRoute(navHostController = navHostController)
        agentsRoute()
        mapsRoute()
        cardsRoute()
        tiersRoute()
        favoritesRoute(navHostController = navHostController, snackbarHostState = snackbarHostState)
        searchRoute(navHostController = navHostController)
    }
}