package com.gblrod.radianthub.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.agents.screen.AgentsScreen

fun NavGraphBuilder.agentsRoute() {
    composable(
        route = Routes.Agents.ROUTE_WITH_ARGUMENT,
        arguments = listOf(
            navArgument(name = "agentUuid") {
                nullable = true
                defaultValue = null
            }
        )
    ) { backStackEntry ->
        val agentUuid = backStackEntry.arguments?.getString("agentUuid")

        AgentsScreen(
            initialAgentUuid = agentUuid
        )
    }
}