package com.josegrillo.usecase

import com.josegrillo.data.repository.CharactersRepository
import com.josegrillo.data.repository.FavoriteRepository
import com.josegrillo.usecase.mapper.CharacterMapper
import com.josegrillo.usecase.usecase.GetCharacterDetailUseCase
import com.josegrillo.usecase.usecase.GetCharacterDetailUseCaseImpl
import com.josegrillo.usecase.utils.DataGenerator.generateCharacterDTO
import com.josegrillo.usecase.utils.DataGenerator.generateRandom
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetCharacterDetailUseCaseTest {
    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @Mock
    private lateinit var charactersRepository: CharactersRepository

    @Mock
    private lateinit var favoriteRepository: FavoriteRepository

    @Mock
    private lateinit var characterMapper: CharacterMapper

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        getCharacterDetailUseCase =
            GetCharacterDetailUseCaseImpl(charactersRepository, favoriteRepository, characterMapper)
    }

    @Test
    fun testGetCharacterDetail() {
        val characterId = generateRandom()

        // WHEN
        Mockito.`when`(
            charactersRepository.getCharacterDetail(characterId)
        ).thenReturn(
            Single.just(
                generateCharacterDTO()
            )
        )

        Mockito.`when`(
            favoriteRepository.isCharacterFavorite(characterId)
        ).thenReturn(
            Single.just(true)
        )

        // THEN
        getCharacterDetailUseCase.getCharacterDetail(characterId)

        // WHAT
        Mockito.verify(charactersRepository).getCharacterDetail(characterId)
    }
}