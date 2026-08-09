package com.gblrod.radianthub.data.favorite.repository

import com.gblrod.radianthub.data.room.dao.FavoriteDao
import com.gblrod.radianthub.data.room.entity.FavoriteEntity
import com.gblrod.radianthub.data.room.mapper.toUiModel
import com.gblrod.radianthub.data.room.model.FavoriteType
import com.gblrod.radianthub.ui.features.favorites.model.FavoriteUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepositoryImpl(
    private val dao: FavoriteDao
) : FavoriteRepository {

    override fun observeFavorites(): Flow<List<FavoriteUiModel>> {
        return dao
            .observeFavorites()
            .map { favorites ->
                favorites.map { it.toUiModel() }
            }
    }

    override fun isFavorite(uuid: String): Flow<Boolean> {
        return dao.isFavorite(uuid)
    }

    override suspend fun toggleFavorite(
        uuid: String,
        name: String,
        imageUrl: String?,
        type: FavoriteType,
        index: Int,
        background: String?
    ) {
        dao.insertFavorite(
            FavoriteEntity(
                uuid = uuid,
                name = name,
                imageUrl = imageUrl,
                type = type,
                index = index,
                background = background
            )
        )
    }

    override suspend fun removeFavorite(uuid: String) {
        dao.deleteFavorite(uuid)
    }
}