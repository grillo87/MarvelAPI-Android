package com.josegrillo.data.repository

import com.josegrillo.data.entity.CharacterDTO
import io.reactivex.Completable
import io.reactivex.Single

interface FavoriteRepository {
    fun isCharacterFavorite(characterId: Int): Single<Boolean>
    fun insertFavorite(characterDTO: CharacterDTO): Completable
    fun deleteFavorite(characterDTO: CharacterDTO): Completable
}