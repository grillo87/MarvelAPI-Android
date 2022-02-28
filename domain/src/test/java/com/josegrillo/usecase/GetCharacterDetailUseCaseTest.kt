package com.josegrillo.usecase

import com.google.gson.Gson
import com.josegrillo.usecase.entity.Character
import com.josegrillo.usecase.entity.Result
import com.josegrillo.usecase.repository.CharactersRepository
import com.josegrillo.usecase.usecase.GetCharacterDetailUseCase
import com.josegrillo.usecase.utils.DataReaderUtils.readFileWithoutNewLineFromResources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetCharacterDetailUseCaseTest {
    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @Mock
    private lateinit var charactersRepository: CharactersRepository

    @ExperimentalCoroutinesApi
    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
        getCharacterDetailUseCase =
            GetCharacterDetailUseCase(charactersRepository)
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetCharacterDetail() {

        val mockCharacter = Gson().fromJson(
            readFileWithoutNewLineFromResources("character.json"),
            Character::class.java
        )

        runTest {
            // WHEN
            Mockito.`when`(
                charactersRepository.getCharacterDetail(Mockito.anyInt())
            ).thenReturn(
                Result.Success(
                    mockCharacter
                )
            )

            // THEN
            val useCaseResult = getCharacterDetailUseCase.invoke(Mockito.anyInt())

            // WHAT
            assert(useCaseResult is Result.Success)
            assert((useCaseResult as Result.Success).data.id == 10)
            assert(useCaseResult.data.id == 10)
            assert(useCaseResult.data.name == "Test Character")
            assert(useCaseResult.data.description == "Test Description")
            assert(useCaseResult.data.thumbnail != null)
            assert(useCaseResult.data.thumbnail!!.path == "https://www.google.com/test")
            assert(useCaseResult.data.thumbnail!!.extension == "jpg")
        }
    }
}