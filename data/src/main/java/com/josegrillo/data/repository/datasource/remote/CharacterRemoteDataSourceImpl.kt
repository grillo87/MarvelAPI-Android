package com.josegrillo.data.repository.datasource.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.josegrillo.data.api.MarvelApi
import com.josegrillo.data.paging.CharacterPagingSource
import com.josegrillo.usecase.entity.Character
import com.josegrillo.usecase.entity.Result
import com.josegrillo.usecase.entity.exception.NoCharacterFoundException
import kotlinx.coroutines.flow.Flow

class CharacterRemoteDataSourceImpl(
    private val characterPagingSource: CharacterPagingSource,
    private val marvelApi: MarvelApi
) :
    CharacterRemoteDataSource {

    @OptIn(ExperimentalPagingApi::class)
    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 40

            ),
            pagingSourceFactory = { characterPagingSource }
        ).flow
    }

    override suspend fun getCharacterDetail(characterId: Int): Result<Character> {
        return try {
            val response = marvelApi.getCharacterDetail(characterId)
            val character = response.body()?.data?.characters?.firstOrNull()
            if (response.isSuccessful && character != null) {
                Result.Success(character)
            } else {
                Result.Failure(NoCharacterFoundException())
            }
        } catch (exception: Exception) {
            Result.Failure(exception)
        }
    }

}