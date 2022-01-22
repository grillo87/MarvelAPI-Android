package com.josegrillo.data.database.favorite

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Maybe

@Dao
interface FavoriteInterfaceDB {

    @Query("SELECT * FROM ${FavoriteDAO.TABLE_NAME} WHERE ${FavoriteDAO.CHARACTER_ID_COLUMN} = :characterId")
    fun getFavoriteById(characterId: Int): Maybe<List<FavoriteDAO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favoriteDAO: FavoriteDAO): Completable

    @Delete
    fun delete(favoriteDAO: FavoriteDAO): Completable
}