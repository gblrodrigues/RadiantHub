package com.gblrod.radianthub.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.gblrod.radianthub.navigation.graph.agentsRoute
import com.gblrod.radianthub.navigation.graph.favoritesRoute
import com.gblrod.radianthub.navigation.graph.homeRoute
import com.gblrod.radianthub.navigation.graph.mapsRoute
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
import com.gblrod.radianthub.ui.features.maps.viewmodel.MapsViewModel

@Composable
fun NavigationGraph(
    agentsViewModel: AgentsViewModel,
    mapsViewModel: MapsViewModel,
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Home.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        homeRoute()

        agentsRoute(
            agentsViewModel = agentsViewModel
        )

        mapsRoute(
            mapsViewModel = mapsViewModel
        )

        favoritesRoute()
    }
}