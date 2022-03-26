package com.josegrillo.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.josegrillo.data.api.MarvelApi
import com.josegrillo.data.entity.CharacterDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterPagingSource(
    private val marvelApi: MarvelApi
) : PagingSource<Int, CharacterDTO>() {

    private val networkPageSize = 20
    private val initialLoadSize = 1

    override fun getRefreshKey(state: PagingState<Int, CharacterDTO>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDTO> {
        val position = params.key ?: initialLoadSize
        val offset =
            if (params.key != null) ((position - 1) * networkPageSize) + 1 else initialLoadSize
        return try {
            withContext(Dispatchers.IO) {
                val response = marvelApi.getCharacters(offset, networkPageSize)
                when {
                    response.isSuccessful -> {
                        response.body()?.dataDTO?.characterDTOS?.let {
                            toLoadResult(params, position, it)
                        } ?: run {
                            LoadResult.Error(Exception("Null Characters List"))
                        }
                    }
                    else -> {
                        LoadResult.Error(Exception("Error code: ${response.code()}"))
                    }
                }
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    private fun toLoadResult(
        params: LoadParams<Int>,
        position: Int,
        charactersDTO: List<CharacterDTO>
    ): LoadResult<Int, CharacterDTO> {
        return LoadResult.Page(
            data = charactersDTO,
            prevKey = null,
            nextKey = if (charactersDTO.isEmpty()) {
                null
            } else {
                position + (params.loadSize / networkPageSize)
            }

        )
    }
}