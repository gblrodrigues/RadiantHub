package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.cards.screen.CardsScreen
import com.gblrod.radianthub.ui.features.cards.viewmodel.CardsViewModel

fun NavGraphBuilder.cardsRoute(
    cardsViewModel: CardsViewModel
) {
    composable(route = Routes.Cards.route) {
        CardsScreen(
            cardsViewModel = cardsViewModel
        )
    }
}