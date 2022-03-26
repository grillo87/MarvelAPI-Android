package com.josegrillo.data

import com.google.gson.Gson
import com.josegrillo.data.di.DataKoinModulesLoader
import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.data.mapper.CharacterMapper
import com.josegrillo.data.utils.DataUtils
import com.josegrillo.data.utils.makeSecurePath
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
            DataKoinModulesLoader.initModules()
        }
    }

    @Test
    fun testCharacterMapper() {
        // WHEN
        val input = Gson().fromJson(
            DataUtils.readFileWithoutNewLineFromResources("character_dto.json"),
            CharacterDTO::class.java
        )

        // THEN
        val output = characterMapper.map(input)

        // WHAT
        assert(output.id == input.id)
        assert(output.name == input.name)
        assert(output.description == input.description)
        assert(output.thumbnail == "${input.thumbnailDTO!!.path!!.makeSecurePath()}.${input.thumbnailDTO!!.extension}")
    }
}