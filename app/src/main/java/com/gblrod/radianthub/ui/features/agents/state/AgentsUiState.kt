package com.gblrod.radianthub.ui.features.agents.state

import com.gblrod.radianthub.domain.agents.model.Agent

sealed class AgentsUiState {
    object Loading : AgentsUiState()

    data class Success(
        val agents: List<Agent>,
        val totalAgents: Int
    ) : AgentsUiState()

    data class Error(
        val messageResId: Int,
        val code: Int? = null
    ) : AgentsUiState()
}