package com.gblrod.radianthub.ui.features.favorites.model

import com.gblrod.radianthub.data.room.model.FavoriteType

data class FavoriteUiModel(
    val name: String,
    val uuid: String,
    val imageUrl: String?,
    val type: FavoriteType,
    val index: Int
)