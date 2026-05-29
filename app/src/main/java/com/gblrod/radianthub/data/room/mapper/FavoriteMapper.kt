package com.gblrod.radianthub.data.room.mapper

import com.gblrod.radianthub.data.room.entity.FavoriteEntity
import com.gblrod.radianthub.ui.features.favorites.model.FavoriteUiModel

fun FavoriteEntity.toUiModel() = FavoriteUiModel(
    uuid = uuid,
    name = name,
    imageUrl = imageUrl,
    type = type,
    index = index
)