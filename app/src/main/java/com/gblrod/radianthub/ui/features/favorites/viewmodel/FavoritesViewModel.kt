package com.gblrod.radianthub.ui.features.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gblrod.radianthub.data.favorite.repository.FavoriteRepository
import com.gblrod.radianthub.data.room.model.FavoriteFilter
import com.gblrod.radianthub.data.room.model.FavoriteType
import com.gblrod.radianthub.ui.features.favorites.model.FavoriteUiModel
import com.gblrod.radianthub.ui.features.favorites.state.FavoritesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: FavoriteRepository
) : ViewModel() {

    private val _favoritesState = MutableStateFlow<FavoritesUiState>(FavoritesUiState.Loading)
    val favoritesState: StateFlow<FavoritesUiState> = _favoritesState

    private val _selectedFilter = MutableStateFlow(FavoriteFilter.ALL)

    val selectedFilter: StateFlow<FavoriteFilter> = _selectedFilter

    private var allFavorites: List<FavoriteUiModel> = emptyList()

    init {
        observeFavorites()
    }

    private fun validateSelectedFilter() {
        when (_selectedFilter.value) {
            FavoriteFilter.CARD -> {
                val hasCards = allFavorites.any {
                    it.type == FavoriteType.CARD
                }

                if (!hasCards) {
                    _selectedFilter.value = FavoriteFilter.ALL
                }
            }
            FavoriteFilter.AGENT -> {
                val hasAgents = allFavorites.any {
                    it.type == FavoriteType.AGENT
                }

                if (!hasAgents) {
                    _selectedFilter.value = FavoriteFilter.ALL
                }
            }

            else -> {}
        }
    }

    private fun applyFilter() {
        validateSelectedFilter()

        val filteredFavorites = when (_selectedFilter.value) {
            FavoriteFilter.ALL -> allFavorites

            FavoriteFilter.AGENT -> allFavorites.filter {
                it.type == FavoriteType.AGENT
            }

            FavoriteFilter.CARD -> allFavorites.filter {
                it.type == FavoriteType.CARD
            }
        }

        _favoritesState.value = FavoritesUiState.Success(
            favorites = filteredFavorites,
            agentCount = allFavorites.count {
                it.type == FavoriteType.AGENT
            },
            cardCount = allFavorites.count {
                it.type == FavoriteType.CARD
            },
            totalFavorites = allFavorites.size
        )
    }

    fun setFilter(filter: FavoriteFilter) {
        _selectedFilter.value = filter
        applyFilter()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            repository.observeFavorites().collect { favorites ->
                allFavorites = favorites
                applyFilter()
            }
        }
    }

    fun removeFavorite(
        favorite: FavoriteUiModel
    ) {
        viewModelScope.launch {
            repository.removeFavorite(uuid = favorite.uuid)
        }
    }

    fun restoreFavorite(
        favorite: FavoriteUiModel
    ) {
        viewModelScope.launch {
            repository.toggleFavorite(
                uuid = favorite.uuid,
                name = favorite.name,
                imageUrl = favorite.imageUrl,
                type = favorite.type,
                index = favorite.index
            )
        }
    }

    fun retry() {
        observeFavorites()
    }
}