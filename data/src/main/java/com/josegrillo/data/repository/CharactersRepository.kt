package com.josegrillo.data.repository

import androidx.paging.PagingData
import com.josegrillo.data.entity.CharacterDTO
import io.reactivex.Flowable
import io.reactivex.Single

interface CharactersRepository {
    fun getCharacters(): Flowable<PagingData<CharacterDTO>>
    fun getCharacterDetail(characterId: Int): Single<CharacterDTO>
}