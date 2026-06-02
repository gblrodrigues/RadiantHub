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
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
import com.gblrod.radianthub.ui.features.cards.viewmodel.CardsViewModel
import com.gblrod.radianthub.ui.features.favorites.viewmodel.FavoritesViewModel
import com.gblrod.radianthub.ui.features.maps.viewmodel.MapsViewModel
import com.gblrod.radianthub.ui.features.search.viewmodel.SearchViewModel

@Composable
fun NavigationGraph(
    agentsViewModel: AgentsViewModel,
    mapsViewModel: MapsViewModel,
    favoritesViewModel: FavoritesViewModel,
    cardsViewModel: CardsViewModel,
    searchViewModel: SearchViewModel,
    paddingValues: PaddingValues,
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState
) {
    NavHost(
        navController = navHostController,
        startDestination = Routes.Home.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ) {
        homeRoute(
            navHostController = navHostController
        )

        agentsRoute(
            agentsViewModel = agentsViewModel
        )

        mapsRoute(
            mapsViewModel = mapsViewModel
        )

        cardsRoute(
            cardsViewModel = cardsViewModel
        )

        favoritesRoute(
            favoritesViewModel = favoritesViewModel,
            agentsViewModel = agentsViewModel,
            navHostController = navHostController,
            snackbarHostState = snackbarHostState
        )

        searchRoute(
            searchViewModel = searchViewModel,
            agentsViewModel = agentsViewModel,
            cardsViewModel = cardsViewModel,
            mapsViewModel = mapsViewModel,
            navHostController = navHostController
        )
    }
}