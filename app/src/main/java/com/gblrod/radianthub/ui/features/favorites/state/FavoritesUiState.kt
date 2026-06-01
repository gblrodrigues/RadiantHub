package com.gblrod.radianthub.ui.features.favorites.state

import com.gblrod.radianthub.ui.features.favorites.model.FavoriteUiModel

sealed class FavoritesUiState {
    object Loading : FavoritesUiState()

    data class Success(
        val favorites: List<FavoriteUiModel>,
        val agentCount: Int,
        val cardCount: Int,
        val totalFavorites: Int
    ) : FavoritesUiState()

    data class Error(
        val messageResId: Int,
        val code: Int? = null
    ) : FavoritesUiState()
}