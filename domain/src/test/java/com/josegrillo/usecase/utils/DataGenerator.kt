package com.josegrillo.usecase.utils

import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.data.entity.ThumbnailDTO
import kotlin.random.Random

object DataGenerator {
    fun generateCharacterDTO() =
        CharacterDTO(
            generateRandom(),
            "Character ${generateRandom()}",
            "Character with description ${generateRandom()}",
            ThumbnailDTO(
                "http://${generateRandom()}",
                "jpg"
            )
        )

    fun generateCharacterBO() =
        CharacterBO(
            generateRandom(),
            "Character ${generateRandom()}",
            "Character with description ${generateRandom()}",
            "http://${generateRandom()}.jpg"
        )

    fun generateRandom() = Random.nextInt(0, 100)
}