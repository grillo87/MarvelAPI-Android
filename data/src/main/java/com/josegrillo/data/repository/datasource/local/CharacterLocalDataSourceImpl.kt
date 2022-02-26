package com.josegrillo.data.repository.datasource.local

import com.josegrillo.data.db.FavoriteDAO
import com.josegrillo.usecase.entity.Favorite
import com.josegrillo.usecase.entity.Result

class CharacterLocalDataSourceImpl(private val favoriteDAO: FavoriteDAO) :
    CharacterLocalDataSource {

    override suspend fun getCharacterIsFavorite(characterId: Int): Result<Boolean> {
        return try {
            favoriteDAO.getFavoriteById(characterId)?.let {
                Result.Success(true)
            } ?: run {
                Result.Success(false)
            }
        } catch (exception: Exception) {
            Result.Failure(exception)
        }
    }

    override suspend fun insertCharacterAsFavorite(favorite: Favorite) {
        favoriteDAO.insert(favorite)
    }

    override suspend fun removeCharacterFromFavorite(favorite: Favorite) {
        favoriteDAO.delete(favorite)
    }
}