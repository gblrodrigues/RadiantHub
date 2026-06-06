package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.tiers.screen.TiersScreen
import com.gblrod.radianthub.ui.features.tiers.viewmodel.TiersViewModel

fun NavGraphBuilder.tiersRoute(
    tiersViewModel: TiersViewModel
) {
    composable(route = Routes.Tiers.route) {
        TiersScreen(
            tiersViewModel = tiersViewModel
        )
    }
}