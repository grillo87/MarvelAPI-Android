package com.josegrillo.usecase

import com.josegrillo.data.repository.FavoriteRepository
import com.josegrillo.usecase.mapper.CharacterMapper
import com.josegrillo.usecase.utils.DataGenerator.generateCharacterDTO
import com.josegrillo.usecase.utils.DataGenerator.generateRandom
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UpdateFavoriteStatusUseCaseTest {

    private lateinit var updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase

    @Mock
    private lateinit var favoriteRepository: FavoriteRepository

    @Mock
    private lateinit var characterMapper: CharacterMapper

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        updateFavoriteStatusUseCase =
            UpdateFavoriteStatusUseCase(favoriteRepository, characterMapper)
    }

    @Test
    fun testInsertFavorite() {

        val characterId = generateRandom()

        // WHEN
        Mockito.`when`(
            favoriteRepository.isCharacterFavorite(characterId)
        ).thenReturn(
            Single.just(true)
        )

        Mockito.`when`(
            favoriteRepository.insertFavorite(generateCharacterDTO())
        ).thenReturn(
            Completable.complete()
        )

        // THEN
        updateFavoriteStatusUseCase.updateFavoriteStatus(characterId)

        // WHAT
        Mockito.verify(favoriteRepository).isCharacterFavorite(characterId)
    }


    @Test
    fun testRemoveFavorite() {

        val characterId = generateRandom()

        // WHEN
        Mockito.`when`(
            favoriteRepository.isCharacterFavorite(characterId)
        ).thenReturn(
            Single.just(false)
        )

        Mockito.`when`(
            favoriteRepository.deleteFavorite(generateCharacterDTO())
        ).thenReturn(
            Completable.complete()
        )

        // THEN
        updateFavoriteStatusUseCase.updateFavoriteStatus(characterId)

        // WHAT
        Mockito.verify(favoriteRepository).isCharacterFavorite(characterId)
    }
}