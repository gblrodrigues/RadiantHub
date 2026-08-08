package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.tiers.screen.TiersScreen

fun NavGraphBuilder.tiersRoute() {
    composable(
        route = Routes.Tiers.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(name = "tierId") {
                type = NavType.IntType
                defaultValue = -1
            }
        )
    ) { backStackEntry ->
        val tierId = backStackEntry.arguments?.getInt("tierId")

        TiersScreen(
            initialTierId = tierId?.takeIf { it != -1 }
        )
    }
}