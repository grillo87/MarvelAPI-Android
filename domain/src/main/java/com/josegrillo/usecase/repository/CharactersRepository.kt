package com.josegrillo.usecase.repository

import androidx.paging.PagingData
import com.josegrillo.usecase.entity.CharacterBO
import com.josegrillo.usecase.entity.Result
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<PagingData<CharacterBO>>
    suspend fun getCharacterDetail(characterId: Int): Result<CharacterBO>
    suspend fun checkFavoriteCharacter(characterId: Int): Result<Boolean>
    suspend fun insertFavorite(characterId: Int)
    suspend fun deleteFavorite(characterId: Int)
}