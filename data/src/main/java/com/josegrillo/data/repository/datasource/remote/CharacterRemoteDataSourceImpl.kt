package com.josegrillo.data.repository.datasource.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.josegrillo.data.api.MarvelApi
import com.josegrillo.data.paging.CharacterPagingSource
import com.josegrillo.data.entity.CharacterDTO
import kotlinx.coroutines.flow.Flow

class CharacterRemoteDataSourceImpl(
    private val characterPagingSource: CharacterPagingSource,
    private val marvelApi: MarvelApi
) :
    CharacterRemoteDataSource {

    private val TAG = CharacterRemoteDataSource::class.java.simpleName

    @OptIn(ExperimentalPagingApi::class)
    override fun getCharacters(): Flow<PagingData<CharacterDTO>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 40

            ),
            pagingSourceFactory = { characterPagingSource }
        ).flow
    }

    override suspend fun getCharacterDetail(characterId: Int): CharacterDTO? {
        return try {
            val response = marvelApi.getCharacterDetail(characterId)
            val character = response.body()?.dataDTO?.characterDTOS?.firstOrNull()
            if (response.isSuccessful && character != null) {
                character
            } else {
                null
            }
        } catch (exception: Exception) {
            Log.e(TAG, exception.localizedMessage ?: "Character Detail Error")
            null
        }
    }

}