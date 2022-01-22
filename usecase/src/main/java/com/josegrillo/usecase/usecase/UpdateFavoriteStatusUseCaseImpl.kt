package com.josegrillo.usecase.usecase

import com.josegrillo.data.repository.FavoriteRepository
import com.josegrillo.usecase.mapper.CharacterMapper
import io.reactivex.Completable

class UpdateFavoriteStatusUseCaseImpl(
    private val favoriteRepository: FavoriteRepository,
    private val characterMapper: CharacterMapper
) :
    UpdateFavoriteStatusUseCase {

    override fun updateFavoriteStatus(characterId: Int): Completable {
        return favoriteRepository.isCharacterFavorite(characterId)
            .flatMapCompletable {
                if (it) {
                    favoriteRepository.deleteFavorite(characterMapper.map(characterId))
                } else {
                    favoriteRepository.insertFavorite(characterMapper.map(characterId))
                }
            }
    }
}