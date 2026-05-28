package com.gblrod.radianthub.ui.features.favorites.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gblrod.radianthub.data.favorite.repository.FavoriteRepository
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

    private val _selectedType = MutableStateFlow<FavoriteType?>(null)
    val selectedType: StateFlow<FavoriteType?> = _selectedType

    init {
        observeFavorites()
    }

    private fun observeFavorites() {
        viewModelScope.launch {
            repository.observeFavorites().collect { favorites ->

                val filteredFavorites =
                    selectedType.value?.let { type ->
                        favorites.filter { it.type == type }
                    } ?: favorites

                _favoritesState.value =
                    FavoritesUiState.Success(favorites = filteredFavorites)
            }
        }
    }

    fun toggleFavorite(
        favorite: FavoriteUiModel
    ) {
        viewModelScope.launch {
            repository.toggleFavorite(
                uuid = favorite.uuid,
                name = favorite.name,
                imageUrl = favorite.imageUrl,
                type = favorite.type
            )
        }
    }

    fun retry() {
        observeFavorites()
    }
}