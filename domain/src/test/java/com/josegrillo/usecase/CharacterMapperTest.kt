package com.josegrillo.usecase

import com.josegrillo.data.di.DataKoinModulesLoader
import com.josegrillo.usecase.di.UseCaseKoinModulesLoader
import com.josegrillo.usecase.mapper.CharacterMapper
import com.josegrillo.usecase.utils.DataGenerator.generateCharacterBO
import com.josegrillo.usecase.utils.DataGenerator.generateCharacterDTO
import com.josegrillo.usecase.utils.DataGenerator.generateRandom
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import kotlin.random.Random

class CharacterMapperTest : AutoCloseKoinTest() {

    private val characterMapper: CharacterMapper by inject()

    @Before
    fun before() {
        startKoin {
            UseCaseKoinModulesLoader.initModules()
            DataKoinModulesLoader.initModules()
        }
    }

    @Test
    fun mapCharacterDTOToBO() {
        // WHEN
        val input = generateCharacterDTO()

        // THEN
        val output = characterMapper.map(input)

        // WHAT
        assert(input.id == output.id)
        assert(input.description == output.description)
        assert(input.name == output.name)
        assert(input.thumbnail.toString() == output.image)
        assert(!output.isFavorite)

    }

    @Test
    fun mapCharacterBOToDTO() {
        // WHEN
        val input = generateCharacterBO()

        // THEN
        val output = characterMapper.map(input)

        // WHAT
        assert(input.id == output.id)
        assert(input.description == output.description)
        assert(input.name == output.name)
        assert(output.thumbnail == null)
    }

    @Test
    fun mapCharacterIdToDTO() {
        // WHEN
        val input = generateRandom()

        // THEN
        val output = characterMapper.map(input)

        // WHAT
        assert(input == output.id)
        assert(output.description == null)
        assert(output.name.isBlank())
        assert(output.thumbnail == null)
    }
}