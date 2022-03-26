package com.josegrillo.marvelapi

import com.google.gson.Gson
import com.josegrillo.marvelapi.di.AppKoinModulesLoader
import com.josegrillo.marvelapi.mapper.CharacterMapper
import com.josegrillo.marvelapi.utils.DataReaderUtils.readFileWithoutNewLineFromResources
import com.josegrillo.usecase.di.UseCaseKoinModulesLoader
import com.josegrillo.usecase.entity.CharacterBO
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class CharacterMapperTest : AutoCloseKoinTest() {

    private val characterMapper: CharacterMapper by inject()

    @Before
    fun before() {
        startKoin {
            AppKoinModulesLoader.initModules()
            UseCaseKoinModulesLoader.initModules()
        }
    }

    @Test
    fun mapCharacterToVO() {
        // WHEN
        val input = Gson().fromJson(
            readFileWithoutNewLineFromResources("character.json"),
            CharacterBO::class.java
        )

        // THEN
        val output = characterMapper.map(input)

        // WHAT
        assert(!output.isFavorite)
        assert(input.id == output.id)
        assert(input.description == output.description)
        assert(input.name == output.name)
        assert(input.thumbnail == output.image)
    }
}