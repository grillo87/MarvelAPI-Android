package com.josegrillo.data.repository.datasource.remote

import androidx.paging.PagingData
import com.josegrillo.usecase.entity.Character
import com.josegrillo.usecase.entity.Result
import kotlinx.coroutines.flow.Flow

interface CharacterRemoteDataSource {
    fun getCharacters(): Flow<PagingData<Character>>
    suspend fun getCharacterDetail(characterId: Int): Result<Character>
}