package com.gblrod.radianthub.ui.features.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gblrod.radianthub.R
import com.gblrod.radianthub.domain.agents.repository.AgentsRepository
import com.gblrod.radianthub.domain.cards.repository.CardsRepository
import com.gblrod.radianthub.domain.maps.repository.MapsRepository
import com.gblrod.radianthub.ui.features.search.model.SearchItem
import com.gblrod.radianthub.ui.features.search.model.SearchType
import com.gblrod.radianthub.ui.features.search.state.SearchUiState
import com.gblrod.radianthub.ui.shared.utils.safeApiCall
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val agentsRepository: AgentsRepository,
    private val mapsRepository: MapsRepository,
    private val cardsRepository: CardsRepository
) : ViewModel() {
    private val _searchState =
        MutableStateFlow<SearchUiState>(SearchUiState.Success(
                query = "",
                results = emptyList()
            )
        )
    val searchState: StateFlow<SearchUiState> = _searchState

    private var allItems: List<SearchItem> = emptyList()

    init {
        loadItems()
    }

    fun updateQuery(query: String) {
        val filteredItems = allItems.filter {
            it.title.contains(
                other = query,
                ignoreCase = true
            )
        }

        _searchState.value =
            SearchUiState.Success(
                query = query,
                results = filteredItems
            )
    }

    private fun loadItems() {
        viewModelScope.launch {
            safeApiCall(
                onHttpError = { code ->
                    _searchState.value =
                        SearchUiState.Error(
                            messageResId = R.string.ui_state_http_exception,
                            code = code
                        )
                },
                onIoError = {
                    _searchState.value =
                        SearchUiState.Error(
                            messageResId = R.string.ui_state_io_exception
                        )
                },
                onGenericError = {
                    _searchState.value =
                        SearchUiState.Error(
                            messageResId = R.string.ui_state_generic_error
                        )
                }
            ) {
                val agents = agentsRepository.getAgents()
                val cards = cardsRepository.getCards()
                val maps = mapsRepository.getMaps()

               val agentsItems = agents.map { agent ->
                    SearchItem(
                        uuid = agent.uuid,
                        title = agent.name,
                        imageUrl = agent.icon.orEmpty(),
                        type = SearchType.AGENT
                    )
                }

                val cardsItems = cards.map { cards ->
                    SearchItem(
                        uuid = cards.uuid,
                        title = cards.name,
                        imageUrl = cards.icon.orEmpty(),
                        type = SearchType.CARD
                    )
                }

                val mapsItems = maps.map { maps ->
                    SearchItem(
                        uuid = maps.uuid,
                        title = maps.name,
                        imageUrl = maps.icon.orEmpty(),
                        type = SearchType.MAP
                    )
                }

                allItems = agentsItems + cardsItems + mapsItems

                _searchState.value =
                    SearchUiState.Success(
                        query = "",
                        results = emptyList()
                    )
            }
        }
    }

    fun clearSearch() {
        _searchState.value =
            SearchUiState.Success(
                query = "",
                results = emptyList()
            )
    }

    fun retry() {
        loadItems()
    }
}