package com.josegrillo.data

import com.josegrillo.data.di.DataKoinModulesLoader
import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.data.entity.ThumbnailDTO
import com.josegrillo.data.mapper.FavoriteMapper
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import kotlin.random.Random

class FavoritesMapperTest : AutoCloseKoinTest() {

    private val favoriteMapper: FavoriteMapper by inject()

    @Before
    fun before() {
        startKoin {
            DataKoinModulesLoader.initModules()
        }
    }

    @Test
    fun mapCharacterDTOToFavorite() {
        // WHEN
        val input = generateCharacterDTO()

        // THEN
        val output = favoriteMapper.map(input)

        // WHAT
        assert(input.id == output.characterId)
    }

    private fun generateCharacterDTO() =
        CharacterDTO(
            generateRandom(),
            "Character ${generateRandom()}",
            "Character with description ${generateRandom()}",
            ThumbnailDTO(
                "http://${generateRandom()}",
                "jpg"
            )
        )

    private fun generateRandom() = Random.nextInt(0, 100)
}