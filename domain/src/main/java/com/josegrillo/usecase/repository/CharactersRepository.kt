package com.josegrillo.usecase.repository

import androidx.paging.PagingData
import com.josegrillo.usecase.entity.Character
import com.josegrillo.usecase.entity.Result
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(): Flow<PagingData<Character>>
    suspend fun getCharacterDetail(characterId: Int): Result<Character>
    suspend fun checkFavoriteCharacter(characterId: Int): Result<Boolean>
    suspend fun insertFavorite(characterId: Int)
    suspend fun deleteFavorite(characterId: Int)
}