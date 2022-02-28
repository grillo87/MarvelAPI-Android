package com.josegrillo.data

import com.google.gson.Gson
import com.josegrillo.data.mapper.FavoriteMapper
import com.josegrillo.data.repository.CharactersRepositoryImpl
import com.josegrillo.data.repository.datasource.local.CharacterLocalDataSource
import com.josegrillo.data.repository.datasource.remote.CharacterRemoteDataSource
import com.josegrillo.data.utils.DataUtils.readFileWithoutNewLineFromResources
import com.josegrillo.usecase.entity.Character
import com.josegrillo.usecase.entity.Result
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

class CharactersRepositoryImplTest : TestCase() {

    private lateinit var charactersRepository: CharactersRepositoryImpl
    private val characterRemoteDataSource: CharacterRemoteDataSource = mock()
    private val characterLocalDataSource: CharacterLocalDataSource = mock()
    private val favoriteMapper: FavoriteMapper = mock()

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
            favoriteMapper
        )

        val mockCharacter = Gson().fromJson(
            readFileWithoutNewLineFromResources("character.json"),
            Character::class.java
        )

        runTest {
            Mockito.`when`((characterRemoteDataSource.getCharacterDetail(Mockito.anyInt())))
                .thenReturn(
                    Result.Success(
                        mockCharacter
                    )
                )
            val characterResult = charactersRepository.getCharacterDetail(Mockito.anyInt())
            assert(characterResult is Result.Success)
            assert((characterResult as Result.Success).data.id == 10)
            assert(characterResult.data.id == 10)
            assert(characterResult.data.name == "Test Character")
            assert(characterResult.data.description == "Test Description")
            assert(characterResult.data.thumbnail != null)
            assert(characterResult.data.thumbnail!!.path == "https://www.google.com/test")
            assert(characterResult.data.thumbnail!!.extension == "jpg")

        }
    }
}