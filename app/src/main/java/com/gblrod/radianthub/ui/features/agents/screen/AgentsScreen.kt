package com.gblrod.radianthub.ui.features.agents.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gblrod.radianthub.domain.agents.model.Agent
import com.gblrod.radianthub.ui.features.agents.components.AgentBottomSheet
import com.gblrod.radianthub.ui.features.agents.components.AgentPage
import com.gblrod.radianthub.ui.features.agents.state.AgentsUiState
import com.gblrod.radianthub.ui.features.agents.viewmodel.AgentsViewModel
import com.gblrod.radianthub.ui.shared.components.ErrorMessage
import com.gblrod.radianthub.ui.shared.components.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgentsScreen(
    agentsViewModel: AgentsViewModel
) {
    val uiState by agentsViewModel.agentsState.collectAsState()
    var selectedAgent by remember { mutableStateOf<Agent?>(null) }

    val sheetState = rememberModalBottomSheetState()

    when (val state = uiState) {
        is AgentsUiState.Loading -> {
            LoadingScreen()
        }

        is AgentsUiState.Error -> {
            val message = if (state.code == null) {
                stringResource(id = state.messageResId)
            } else {
                stringResource(id = state.messageResId, state.code)
            }

            ErrorMessage(
                message = message,
                onRetry = { agentsViewModel.retry() }
            )
        }

        is AgentsUiState.Success -> {
            val pagerState = rememberPagerState { state.agents.size }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                AgentPage(
                    agent = state.agents[page],
                    onViewSkills = { selectedAgent = it},
                    currentPage = pagerState.currentPage,
                    pageCount = state.agents.size,
                    agentsViewModel = agentsViewModel
                )
            }

            selectedAgent?.let { selected ->
                AgentBottomSheet(
                    agent = selected,
                    sheetState = sheetState,
                    onDismiss = { selectedAgent = null }
                )
            }
        }
    }
}