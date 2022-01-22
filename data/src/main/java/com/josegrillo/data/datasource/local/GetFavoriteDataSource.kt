package com.josegrillo.data.datasource.local

import com.josegrillo.data.database.favorite.FavoriteDAO
import io.reactivex.Completable
import io.reactivex.Maybe

interface GetFavoriteDataSource {
    fun getFavoritesCharacters(characterId: Int): Maybe<List<FavoriteDAO>>
    fun insertFavorite(favoriteDAO: FavoriteDAO): Completable
    fun removeFavorite(favoriteDAO: FavoriteDAO): Completable
}