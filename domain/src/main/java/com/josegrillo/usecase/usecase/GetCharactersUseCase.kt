package com.josegrillo.usecase.usecase

import com.josegrillo.usecase.repository.CharactersRepository

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {
    operator fun invoke() = charactersRepository.getCharacters()
}