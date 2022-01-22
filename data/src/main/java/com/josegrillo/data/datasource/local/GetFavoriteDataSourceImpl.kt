package com.josegrillo.data.datasource.local

import com.josegrillo.data.database.favorite.FavoriteDAO
import com.josegrillo.data.database.favorite.FavoriteInterfaceDB
import io.reactivex.Completable
import io.reactivex.Maybe

class GetFavoriteDataSourceImpl(
    private val favoriteInterfaceDB: FavoriteInterfaceDB
) : GetFavoriteDataSource {

    override fun getFavoritesCharacters(characterId: Int): Maybe<List<FavoriteDAO>> {
        return favoriteInterfaceDB.getFavoriteById(characterId)
    }

    override fun insertFavorite(favoriteDAO: FavoriteDAO): Completable {
        return favoriteInterfaceDB.insert(favoriteDAO)
    }

    override fun removeFavorite(favoriteDAO: FavoriteDAO): Completable {
        return favoriteInterfaceDB.delete(favoriteDAO)
    }

}