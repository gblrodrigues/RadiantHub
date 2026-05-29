package com.gblrod.radianthub.ui.features.agents.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gblrod.radianthub.R
import com.gblrod.radianthub.data.favorite.repository.FavoriteRepository
import com.gblrod.radianthub.data.room.model.FavoriteType
import com.gblrod.radianthub.domain.agents.model.Agent
import com.gblrod.radianthub.domain.agents.repository.AgentsRepository
import com.gblrod.radianthub.ui.features.agents.state.AgentsUiState
import com.gblrod.radianthub.ui.shared.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AgentsViewModel(
    private val repository: AgentsRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {
    private val _agentsState = MutableStateFlow<AgentsUiState>(AgentsUiState.Loading)
    val agentsState: StateFlow<AgentsUiState> = _agentsState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val favorites =
        favoriteRepository.observeFavorites().stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = emptyList()
            )

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

    fun isFavorite(uuid: String): Flow<Boolean> {
        return favoriteRepository.isFavorite(uuid)
    }

    fun toggleFavorite(agent: Agent) {
        viewModelScope.launch {
            val isFavorite = favoriteRepository.isFavorite(agent.uuid).first()

            if (isFavorite) {
                favoriteRepository.removeFavorite(
                    uuid = agent.uuid
                )

            } else {
                favoriteRepository.toggleFavorite(
                    uuid = agent.uuid,
                    name = agent.name,
                    imageUrl = agent.portrait,
                    type = FavoriteType.AGENT,
                    index = favorites.value.size
                )
            }
        }
    }

    fun getAgentByUuid(uuid: String): Agent? {
        val state = agentsState.value

        return if (state is AgentsUiState.Success) {
            state.agents.find { it.uuid == uuid }
        } else {
            null
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