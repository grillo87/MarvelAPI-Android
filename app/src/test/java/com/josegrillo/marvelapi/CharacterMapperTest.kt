package com.josegrillo.marvelapi

import com.josegrillo.data.di.DataKoinModulesLoader
import com.josegrillo.marvelapi.di.modules.AppKoinModulesLoader
import com.josegrillo.marvelapi.mapper.CharacterMapper
import com.josegrillo.usecase.di.UseCaseKoinModulesLoader
import com.josegrillo.usecase.entity.CharacterBO
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
            AppKoinModulesLoader.initModules()
            UseCaseKoinModulesLoader.initModules()
            DataKoinModulesLoader.initModules()
        }
    }

    @Test
    fun mapCharacterBOToVO() {
        // WHEN
        val input = generateCharacterBO()

        // THEN
        val output = characterMapper.map(input)

        // WHAT
        assert(input.isFavorite == output.isFavorite)
        assert(input.id == output.id)
        assert(input.description == output.description)
        assert(input.name == output.name)
        assert(input.image == output.image)
    }

    private fun generateCharacterBO() =
        CharacterBO(
            generateRandom(),
            "Character ${generateRandom()}",
            "Character with description ${generateRandom()}",
            "http://marvel${generateRandom()}.jpg",

            )

    private fun generateRandom() = Random.nextInt(0, 100)
}