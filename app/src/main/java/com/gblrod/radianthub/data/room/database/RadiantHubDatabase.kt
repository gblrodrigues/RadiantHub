package com.gblrod.radianthub.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gblrod.radianthub.data.room.dao.FavoriteDao
import com.gblrod.radianthub.data.room.entity.FavoriteEntity

@Database(
    entities = [FavoriteEntity::class],
    version = 2,
    exportSchema = false
)
abstract class RadiantHubDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}