package com.josegrillo.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.josegrillo.data.datasource.remote.GetCharacterDetailDataSource
import com.josegrillo.data.datasource.remote.GetCharacterPagingSource
import com.josegrillo.data.entity.CharacterDTO
import io.reactivex.Flowable
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi

class CharactersRepositoryImpl(
    private val getCharactersPagingSource: GetCharacterPagingSource,
    private val getCharacterDetailDataSource: GetCharacterDetailDataSource
) :
    CharactersRepository {

    @ExperimentalCoroutinesApi
    override fun getCharacters(): Flowable<PagingData<CharacterDTO>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                initialLoadSize = 40

            ),
            pagingSourceFactory = { getCharactersPagingSource }
        ).flowable
    }

    override fun getCharacterDetail(characterId: Int): Single<CharacterDTO> {
        return getCharacterDetailDataSource.getCharacterDetail(characterId)
            .map {
                return@map it.dataDTO.charactersDTO.firstOrNull()
            }
    }
}