package com.josegrillo.data.repository.datasource.local

import com.josegrillo.data.entity.FavoriteDTO

interface CharacterLocalDataSource {
    suspend fun getCharacterIsFavorite(characterId: Int): Boolean
    suspend fun insertCharacterAsFavorite(favoriteDTO: FavoriteDTO)
    suspend fun removeCharacterFromFavorite(favoriteDTO: FavoriteDTO)
}