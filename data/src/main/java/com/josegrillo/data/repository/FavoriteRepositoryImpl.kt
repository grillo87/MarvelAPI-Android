package com.josegrillo.data.repository

import com.josegrillo.data.datasource.local.GetFavoriteDataSource
import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.data.mapper.FavoriteMapper
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteRepositoryImpl(
    private val favoriteDataSource: GetFavoriteDataSource,
    private val favoriteMapper: FavoriteMapper
) : FavoriteRepository {

    override fun isCharacterFavorite(characterId: Int): Single<Boolean> {
        return favoriteDataSource.getFavoritesCharacters(characterId).map {
            it.isNotEmpty()
        }.switchIfEmpty(Single.just(false))
    }

    override fun insertFavorite(characterDTO: CharacterDTO): Completable {
        return favoriteDataSource.insertFavorite(favoriteMapper.map(characterDTO))
    }

    override fun deleteFavorite(characterDTO: CharacterDTO): Completable {
        return favoriteDataSource.removeFavorite(favoriteMapper.map(characterDTO))
    }
}