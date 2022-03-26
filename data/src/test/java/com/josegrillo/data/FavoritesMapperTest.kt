package com.josegrillo.data

import com.josegrillo.data.di.DataKoinModulesLoader
import com.josegrillo.data.mapper.FavoriteMapper
import com.josegrillo.data.utils.DataUtils.generateRandomNumber
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class FavoritesMapperTest : AutoCloseKoinTest() {

    private val favoriteMapper: FavoriteMapper by inject()

    @Before
    fun before() {
        startKoin {
            DataKoinModulesLoader.initModules()
        }
    }

    @Test
    fun testFavoriteMapper() {
        // WHEN
        val input = generateRandomNumber()

        // THEN
        val output = favoriteMapper.map(input)

        // WHAT
        assert(input == output.characterId)
    }
}