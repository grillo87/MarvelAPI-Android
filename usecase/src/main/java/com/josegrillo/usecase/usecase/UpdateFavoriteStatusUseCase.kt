package com.josegrillo.usecase.usecase

import io.reactivex.Completable

interface UpdateFavoriteStatusUseCase {
    fun updateFavoriteStatus(characterId: Int): Completable
}