package com.gblrod.radianthub.navigation.graph

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gblrod.radianthub.navigation.NavigationKeys
import com.gblrod.radianthub.navigation.Routes
import com.gblrod.radianthub.ui.features.agents.screen.AgentsScreen
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.agentsRoute() {
    composable(
        route = Routes.Agents.ROUTE
    ) { backStackEntry ->

        val selectedAgentUuid by backStackEntry
            .savedStateHandle
            .getStateFlow<String?>(
                key = NavigationKeys.SELECTED_AGENT_UUID,
                initialValue = null
            )
            .collectAsState()

        val agentsViewModel: AgentsViewModel = koinViewModel()

        LaunchedEffect(selectedAgentUuid) {
            selectedAgentUuid?.let { uuid ->
                agentsViewModel.selectAgent(uuid)

                backStackEntry.savedStateHandle[
                    NavigationKeys.SELECTED_AGENT_UUID
                ] = null
            }
        }

        AgentsScreen()
    }
}