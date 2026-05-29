package com.gblrod.radianthub.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gblrod.radianthub.data.room.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query(value = """
        SELECT *
        FROM favorites
        ORDER BY `index` ASC
    """
    )

    fun observeFavorites(): Flow<List<FavoriteEntity>>

    @Query(value = """
        SELECT EXISTS(
            SELECT 1
            FROM favorites
            WHERE uuid = :uuid
        )
    """
    )

    fun isFavorite(uuid: String): Flow<Boolean>

    @Query(
        value = """
        SELECT EXISTS(
            SELECT 1
            FROM favorites
            WHERE uuid = :uuid
        )
    """
    )

    suspend fun exists(uuid: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteEntity)

    @Query(
        value = """
        DELETE FROM favorites
        WHERE uuid = :uuid
    """
    )

    suspend fun deleteFavorite(uuid: String)
}