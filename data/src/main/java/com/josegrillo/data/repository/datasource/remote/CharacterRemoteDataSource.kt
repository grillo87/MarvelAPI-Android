package com.josegrillo.data.repository.datasource.remote

import androidx.paging.PagingData
import com.josegrillo.data.entity.CharacterDTO
import kotlinx.coroutines.flow.Flow

interface CharacterRemoteDataSource {
    fun getCharacters(): Flow<PagingData<CharacterDTO>>
    suspend fun getCharacterDetail(characterId: Int): CharacterDTO?
}