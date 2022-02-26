package com.josegrillo.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.josegrillo.data.api.MarvelApi
import com.josegrillo.usecase.entity.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterPagingSource(
    private val marvelApi: MarvelApi
) : PagingSource<Int, Character>() {

    private val networkPageSize = 20
    private val initialLoadSize = 1

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: initialLoadSize
        val offset =
            if (params.key != null) ((position - 1) * networkPageSize) + 1 else initialLoadSize
        return try {
            withContext(Dispatchers.IO) {
                val response = marvelApi.getCharacters(offset, networkPageSize)
                when {
                    response.isSuccessful -> {
                        response.body()?.data?.characters?.let {
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
        characters: List<Character>
    ): LoadResult<Int, Character> {
        return LoadResult.Page(
            data = characters,
            prevKey = null,
            nextKey = if (characters.isEmpty()) {
                null
            } else {
                position + (params.loadSize / networkPageSize)
            }

        )
    }
}