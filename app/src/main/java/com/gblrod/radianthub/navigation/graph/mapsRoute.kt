package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.maps.screen.MapsScreen

fun NavGraphBuilder.mapsRoute() {
    composable(route = Routes.Maps.route) {
        MapsScreen()
    }
}