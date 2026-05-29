package com.gblrod.radianthub.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gblrod.radianthub.data.room.model.FavoriteType

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val uuid: String,
    val name: String,
    val imageUrl: String?,
    val type: FavoriteType,
    val index: Int
)