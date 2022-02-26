package com.josegrillo.data.repository.datasource.local

import com.josegrillo.usecase.entity.Favorite
import com.josegrillo.usecase.entity.Result

interface CharacterLocalDataSource {
    suspend fun getCharacterIsFavorite(characterId: Int): Result<Boolean>
    suspend fun insertCharacterAsFavorite(favorite: Favorite)
    suspend fun removeCharacterFromFavorite(favorite: Favorite)
}