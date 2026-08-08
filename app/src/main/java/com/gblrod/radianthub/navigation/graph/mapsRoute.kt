package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.maps.screen.MapsScreen

fun NavGraphBuilder.mapsRoute() {
    composable(
        route = Routes.Maps.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(name = "mapUuid") {
                nullable = true
                defaultValue = null
            }
        )
    ) { backStackEntry ->
        val mapUuid = backStackEntry.arguments?.getString("mapUuid")

        MapsScreen(
            initialMapUuid = mapUuid
        )
    }
}