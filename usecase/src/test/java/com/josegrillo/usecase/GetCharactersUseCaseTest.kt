package com.josegrillo.usecase

import androidx.paging.PagingData
import com.josegrillo.data.repository.CharactersRepository
import com.josegrillo.usecase.mapper.CharacterMapper
import com.josegrillo.usecase.usecase.GetCharactersUseCase
import com.josegrillo.usecase.usecase.GetCharactersUseCaseImpl
import com.josegrillo.usecase.utils.DataGenerator.generateCharacterDTO
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetCharactersUseCaseTest {
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Mock
    private lateinit var charactersRepository: CharactersRepository

    @Mock
    private lateinit var characterMapper: CharacterMapper

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        getCharactersUseCase =
            GetCharactersUseCaseImpl(charactersRepository, characterMapper)
    }

    @Test
    fun testGetCharacters() {
        // WHEN
        Mockito.`when`(
            charactersRepository.getCharacters()
        ).thenReturn(
            Flowable.just(
                PagingData.from(listOf(generateCharacterDTO()))
            )
        )

        // THEN
        getCharactersUseCase.getCharacters()

        // WHAT
        Mockito.verify(charactersRepository).getCharacters()
    }
}