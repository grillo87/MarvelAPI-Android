package com.josegrillo.usecase.usecase

import com.josegrillo.usecase.repository.CharactersRepository

class RemoveCharacterAsFavoriteUseCase(private val charactersRepository: CharactersRepository) {
    suspend operator fun invoke(characterId: Int) =
        charactersRepository.deleteFavorite(characterId)
}