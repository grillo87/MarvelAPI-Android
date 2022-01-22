package com.josegrillo.usecase.usecase

import com.josegrillo.usecase.entity.CharacterBO
import io.reactivex.Single

interface GetCharacterDetailUseCase {
    fun getCharacterDetail(characterId: Int): Single<CharacterBO>
}