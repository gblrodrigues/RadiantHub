package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.maps.screen.MapsScreen
import com.gblrod.radianthub.ui.features.maps.viewmodel.MapsViewModel

fun NavGraphBuilder.mapsRoute(
    mapsViewModel: MapsViewModel
) {
    composable(route = Routes.Maps.route) {
        MapsScreen(
            mapsViewModel = mapsViewModel
        )
    }
}