package com.josegrillo.data.datasource.remote

import com.josegrillo.data.entity.ResultDTO
import io.reactivex.Single

interface GetCharacterDetailDataSource {
    fun getCharacterDetail(characterId: Int): Single<ResultDTO>
}