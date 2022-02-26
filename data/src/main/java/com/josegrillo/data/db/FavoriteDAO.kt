package com.josegrillo.data.db

import androidx.room.*
import com.josegrillo.usecase.entity.Favorite

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM ${Favorite.TABLE_NAME} WHERE ${Favorite.CHARACTER_ID_COLUMN} = :characterId")
    suspend fun getFavoriteById(characterId: Int): Favorite?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)
}