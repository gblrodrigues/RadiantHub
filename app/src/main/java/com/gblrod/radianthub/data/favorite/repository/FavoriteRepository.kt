package com.gblrod.radianthub.data.favorite.repository

import com.gblrod.radianthub.data.room.model.FavoriteType
import com.gblrod.radianthub.ui.features.favorites.model.FavoriteUiModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    fun observeFavorites(): Flow<List<FavoriteUiModel>>

    fun isFavorite(uuid: String): Flow<Boolean>

    suspend fun toggleFavorite(
        uuid: String,
        name: String,
        imageUrl: String?,
        type: FavoriteType
    )
}