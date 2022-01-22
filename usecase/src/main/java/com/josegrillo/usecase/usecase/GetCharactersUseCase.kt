package com.josegrillo.usecase.usecase

import androidx.paging.PagingData
import com.josegrillo.usecase.entity.CharacterBO
import io.reactivex.Flowable

interface GetCharactersUseCase {
    fun getCharacters(): Flowable<PagingData<CharacterBO>>
}