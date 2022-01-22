package com.josegrillo.usecase.usecase

import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.data.repository.CharactersRepository
import com.josegrillo.data.repository.FavoriteRepository
import com.josegrillo.usecase.entity.CharacterBO
import com.josegrillo.usecase.mapper.CharacterMapper
import io.reactivex.Single

class GetCharacterDetailUseCaseImpl(
    private val charactersRepository: CharactersRepository,
    private val favoriteRepository: FavoriteRepository,
    private val characterMapper: CharacterMapper
) :
    GetCharacterDetailUseCase {
    override fun getCharacterDetail(characterId: Int): Single<CharacterBO> {
        return favoriteRepository.isCharacterFavorite(characterId)
            .zipWith(
                charactersRepository.getCharacterDetail(characterId),
                { isCharacterFavorite, characterDTO ->
                    updateUserIsFavorite(isCharacterFavorite, characterDTO)
                }
            )
    }

    private fun updateUserIsFavorite(
        isCharacterFavorite: Boolean,
        characterDTO: CharacterDTO
    ): CharacterBO {
        return characterMapper.map(characterDTO).apply {
            isFavorite = isCharacterFavorite
        }
    }
}