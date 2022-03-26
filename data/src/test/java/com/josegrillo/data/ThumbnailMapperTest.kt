package com.josegrillo.data

import com.google.gson.Gson
import com.josegrillo.data.di.DataKoinModulesLoader
import com.josegrillo.data.entity.ThumbnailDTO
import com.josegrillo.data.mapper.ThumbnailMapper
import com.josegrillo.data.utils.DataUtils
import com.josegrillo.data.utils.makeSecurePath
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class ThumbnailMapperTest : AutoCloseKoinTest() {

    private val thumbnailMapper: ThumbnailMapper by inject()

    @Before
    fun before() {
        startKoin {
            DataKoinModulesLoader.initModules()
        }
    }

    @Test
    fun testThumbnailMapper() {
        // WHEN
        val input = Gson().fromJson(
            DataUtils.readFileWithoutNewLineFromResources("thumbnail.json"),
            ThumbnailDTO::class.java
        )

        // THEN
        val output = thumbnailMapper.map(input)

        // WHAT
        assert(output != null)
        assert(output!!.isNotEmpty())
        assert(output.startsWith("https"))
        assert("${input.path!!.makeSecurePath()}.${input.extension}" == output)

    }
}