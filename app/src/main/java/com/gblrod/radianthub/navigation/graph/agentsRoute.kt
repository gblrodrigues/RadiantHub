package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.agents.screen.AgentsScreen
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel

fun NavGraphBuilder.agentsRoute(
    agentsViewModel: AgentsViewModel
) {
    composable(route = Routes.Agents.route) {
        AgentsScreen(
            agentsViewModel = agentsViewModel
        )
    }
}