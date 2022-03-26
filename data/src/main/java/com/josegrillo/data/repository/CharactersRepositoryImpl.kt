package com.josegrillo.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.josegrillo.data.mapper.FavoriteMapper
import com.josegrillo.data.repository.datasource.local.CharacterLocalDataSource
import com.josegrillo.data.repository.datasource.remote.CharacterRemoteDataSource
import com.josegrillo.data.mapper.CharacterMapper
import com.josegrillo.usecase.entity.CharacterBO
import com.josegrillo.usecase.entity.exception.NoCharacterFoundException
import com.josegrillo.usecase.repository.CharactersRepository
import com.josegrillo.usecase.entity.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class CharactersRepositoryImpl(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val characterLocalDataSource: CharacterLocalDataSource,
    private val favoriteMapper: FavoriteMapper,
    private val characterMapper: CharacterMapper
) : CharactersRepository {

    override fun getCharacters(): Flow<PagingData<CharacterBO>> {
        return characterRemoteDataSource.getCharacters().map {
            it.map(characterMapper::map)
        }
    }

    override suspend fun getCharacterDetail(characterId: Int): Result<CharacterBO> {
        return characterRemoteDataSource.getCharacterDetail(characterId)?.let {
            Result.Success(characterMapper.map(it))
        } ?: Result.Failure(NoCharacterFoundException())
    }

    override suspend fun checkFavoriteCharacter(characterId: Int): Result<Boolean> {
        return Result.Success(characterLocalDataSource.getCharacterIsFavorite(characterId))
    }

    override suspend fun insertFavorite(characterId: Int) {
        characterLocalDataSource.insertCharacterAsFavorite(favoriteMapper.map(characterId))
    }

    override suspend fun deleteFavorite(characterId: Int) {
        characterLocalDataSource.removeCharacterFromFavorite(favoriteMapper.map(characterId))
    }
}