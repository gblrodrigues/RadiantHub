package com.gblrod.radianthub.ui.features.agents.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gblrod.radianthub.R
import com.gblrod.radianthub.domain.agents.repository.AgentsRepository
import com.gblrod.radianthub.ui.features.agents.state.AgentsUiState
import com.gblrod.radianthub.ui.shared.utils.safeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AgentsViewModel(
    private val repository: AgentsRepository
) : ViewModel() {
    private val _agentsState = MutableStateFlow<AgentsUiState>(AgentsUiState.Loading)
    val agentsState: StateFlow<AgentsUiState> = _agentsState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        if (_agentsState.value !is AgentsUiState.Success) {
            fetchAgents()
        }
    }

    fun fetchAgents() {
        viewModelScope.launch {
            _agentsState.value =
                AgentsUiState.Loading

            safeApiCall(
                onHttpError = { code ->
                    _agentsState.value =
                        AgentsUiState.Error(
                            messageResId = R.string.ui_state_http_exception,
                            code = code
                        )
                },

                onIoError = {
                    _agentsState.value =
                        AgentsUiState.Error(messageResId = R.string.ui_state_io_exception)
                },

                onGenericError = {
                    _agentsState.value =
                        AgentsUiState.Error(messageResId = R.string.ui_state_generic_error)
                }
            ) {
                val agents = repository.getAgents()
                _agentsState.value =
                    AgentsUiState.Success(
                        agents = agents,
                        totalAgents = agents.size
                    )
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun clearSearch() {
        _searchQuery.value = ""
    }

    fun retry() {
        fetchAgents()
    }
}