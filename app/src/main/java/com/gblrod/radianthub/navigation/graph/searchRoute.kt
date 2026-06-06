package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
import com.gblrod.radianthub.ui.features.cards.viewmodel.CardsViewModel
import com.gblrod.radianthub.ui.features.maps.viewmodel.MapsViewModel
import com.gblrod.radianthub.ui.features.search.screen.SearchScreen
import com.gblrod.radianthub.ui.features.search.viewmodel.SearchViewModel
import com.gblrod.radianthub.ui.features.tiers.viewmodel.TiersViewModel

fun NavGraphBuilder.searchRoute(
    searchViewModel: SearchViewModel,
    agentsViewModel: AgentsViewModel,
    cardsViewModel: CardsViewModel,
    mapsViewModel: MapsViewModel,
    tiersViewModel: TiersViewModel,
    navHostController: NavHostController
) {
    composable(route = Routes.Search.route) {
        SearchScreen(
            searchViewModel = searchViewModel,
            agentsViewModel = agentsViewModel,
            cardsViewModel = cardsViewModel,
            mapsViewModel = mapsViewModel,
            tiersViewModel = tiersViewModel,
            navHostController = navHostController,
        )
    }
}