package com.josegrillo.data

import com.google.gson.Gson
import com.josegrillo.data.mapper.FavoriteMapper
import com.josegrillo.data.repository.CharactersRepositoryImpl
import com.josegrillo.data.repository.datasource.local.CharacterLocalDataSource
import com.josegrillo.data.repository.datasource.remote.CharacterRemoteDataSource
import com.josegrillo.data.utils.DataUtils.readFileWithoutNewLineFromResources
import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.data.mapper.CharacterMapper
import com.josegrillo.usecase.entity.CharacterBO
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.Mockito
import com.josegrillo.usecase.entity.Result

class CharactersRepositoryImplTest : TestCase() {

    private lateinit var charactersRepository: CharactersRepositoryImpl
    private val characterRemoteDataSource: CharacterRemoteDataSource = mock()
    private val characterLocalDataSource: CharacterLocalDataSource = mock()
    private val favoriteMapper: FavoriteMapper = mock()
    private val characterMapper: CharacterMapper = mock()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetCharacterDetail() {

        charactersRepository = CharactersRepositoryImpl(
            characterRemoteDataSource,
            characterLocalDataSource,
            favoriteMapper,
            characterMapper
        )

        val mockCharacterDTO = Gson().fromJson(
            readFileWithoutNewLineFromResources("character_dto.json"),
            CharacterDTO::class.java
        )

        val mockCharacterBO = Gson().fromJson(
            readFileWithoutNewLineFromResources("character_bo.json"),
            CharacterBO::class.java
        )

        runTest {
            Mockito.`when`((characterRemoteDataSource.getCharacterDetail(Mockito.anyInt())))
                .thenReturn(
                    mockCharacterDTO
                )

            Mockito.`when`((characterMapper.map(mockCharacterDTO)))
                .thenReturn(
                    mockCharacterBO
                )

            val characterResult = charactersRepository.getCharacterDetail(Mockito.anyInt())
            assert(characterResult is Result.Success)
            assert((characterResult as Result.Success).data.id == 10)
            assert(characterResult.data.id == 10)
            assert(characterResult.data.name == "Test Character")
            assert(characterResult.data.description == "Test Description")
            assert(characterResult.data.thumbnail != null)
            assert(characterResult.data.thumbnail == "https://www.google.com/test.jpg")

        }
    }
}