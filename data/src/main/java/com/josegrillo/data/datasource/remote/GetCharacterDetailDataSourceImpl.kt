package com.josegrillo.data.datasource.remote

import com.josegrillo.data.entity.ResultDTO
import com.josegrillo.data.network.MarvelApi
import io.reactivex.Single

class GetCharacterDetailDataSourceImpl(private val marvelApi: MarvelApi) :
    GetCharacterDetailDataSource {
    override fun getCharacterDetail(characterId: Int): Single<ResultDTO> {
        return marvelApi.getCharacterDetail(characterId)
    }
}