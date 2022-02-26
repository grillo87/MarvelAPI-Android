package com.josegrillo.data.repository

import androidx.paging.PagingData
import com.josegrillo.data.mapper.FavoriteMapper
import com.josegrillo.data.repository.datasource.local.CharacterLocalDataSource
import com.josegrillo.data.repository.datasource.remote.CharacterRemoteDataSource
import com.josegrillo.usecase.entity.Character
import com.josegrillo.usecase.entity.Result
import com.josegrillo.usecase.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow


class CharactersRepositoryImpl(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val characterLocalDataSource: CharacterLocalDataSource,
    private val favoriteMapper: FavoriteMapper
) : CharactersRepository {

    override fun getCharacters(): Flow<PagingData<Character>> {
        return characterRemoteDataSource.getCharacters()
    }

    override suspend fun getCharacterDetail(characterId: Int): Result<Character> {
        return characterRemoteDataSource.getCharacterDetail(characterId)
    }

    override suspend fun checkFavoriteCharacter(characterId: Int): Result<Boolean> {
        return characterLocalDataSource.getCharacterIsFavorite(characterId)
    }

    override suspend fun insertFavorite(characterId: Int) {
        characterLocalDataSource.insertCharacterAsFavorite(favoriteMapper.map(characterId))
    }

    override suspend fun deleteFavorite(characterId: Int) {
        characterLocalDataSource.removeCharacterFromFavorite(favoriteMapper.map(characterId))
    }
}