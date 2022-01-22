package com.josegrillo.data.datasource.remote

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.data.entity.ResultDTO
import com.josegrillo.data.network.MarvelApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class GetCharacterPagingSource(private val marvelApi: MarvelApi) :
    RxPagingSource<Int, CharacterDTO>() {

    private val networkPageSize = 20
    private val initialLoadSize = 1

    override fun getRefreshKey(state: PagingState<Int, CharacterDTO>): Int? {
        return null
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, CharacterDTO>> {
        val position = params.key ?: initialLoadSize
        val offset =
            if (params.key != null) ((position - 1) * networkPageSize) + 1 else initialLoadSize

        return marvelApi
            .getCharacters(offset, networkPageSize)
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResult(params, position, it)
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    private fun toLoadResult(
        params: LoadParams<Int>,
        position: Int,
        resultDTO: ResultDTO
    ): LoadResult<Int, CharacterDTO> {
        return LoadResult.Page(
            data = resultDTO.dataDTO.charactersDTO,
            prevKey = null,
            nextKey = if (resultDTO.dataDTO.charactersDTO.isEmpty()) {
                null
            } else {
                position + (params.loadSize / networkPageSize)
            }

        )
    }
}