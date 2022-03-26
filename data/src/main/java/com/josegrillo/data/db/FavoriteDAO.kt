package com.josegrillo.data.db

import androidx.room.*
import com.josegrillo.data.entity.FavoriteDTO

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM ${FavoriteDTO.TABLE_NAME} WHERE ${FavoriteDTO.CHARACTER_ID_COLUMN} = :characterId")
    suspend fun getFavoriteById(characterId: Int): FavoriteDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteDTO: FavoriteDTO)

    @Delete
    suspend fun delete(favoriteDTO: FavoriteDTO)
}