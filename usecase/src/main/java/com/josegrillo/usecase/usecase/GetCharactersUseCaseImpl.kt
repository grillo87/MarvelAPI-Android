package com.josegrillo.usecase.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.josegrillo.data.repository.CharactersRepository
import com.josegrillo.usecase.entity.CharacterBO
import com.josegrillo.usecase.mapper.CharacterMapper
import io.reactivex.Flowable

class GetCharactersUseCaseImpl(
    private val charactersRepository: CharactersRepository,
    private val characterMapper: CharacterMapper
) :
    GetCharactersUseCase {
    override fun getCharacters(): Flowable<PagingData<CharacterBO>> {
        return charactersRepository.getCharacters()
            .map { it.map(characterMapper::map) }
    }
}