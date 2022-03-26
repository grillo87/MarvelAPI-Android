package com.josegrillo.data.repository.datasource.local

import android.util.Log
import com.josegrillo.data.db.FavoriteDAO
import com.josegrillo.data.entity.FavoriteDTO

class CharacterLocalDataSourceImpl(private val favoriteDAO: FavoriteDAO) :
    CharacterLocalDataSource {

    private val TAG = CharacterLocalDataSourceImpl::class.java.simpleName

    override suspend fun getCharacterIsFavorite(characterId: Int): Boolean {
        return try {
            favoriteDAO.getFavoriteById(characterId) != null
        } catch (exception: Exception) {
            Log.e(TAG, exception.localizedMessage ?: "Character Favorite Error")
            false
        }
    }

    override suspend fun insertCharacterAsFavorite(favoriteDTO: FavoriteDTO) {
        favoriteDAO.insert(favoriteDTO)
    }

    override suspend fun removeCharacterFromFavorite(favoriteDTO: FavoriteDTO) {
        favoriteDAO.delete(favoriteDTO)
    }
}