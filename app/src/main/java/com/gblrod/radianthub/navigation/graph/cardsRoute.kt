package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.cards.screen.CardsScreen

fun NavGraphBuilder.cardsRoute() {
    composable(
        route = Routes.Cards.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(name = "cardUuid") {
                nullable = true
                defaultValue = null
            }
        )
    ) { backStackEntry ->
        val cardUuid = backStackEntry.arguments?.getString("cardUuid")

        CardsScreen(
            initialCardUuid = cardUuid
        )
    }
}