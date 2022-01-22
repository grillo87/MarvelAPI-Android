package com.josegrillo.usecase.mapper

import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.usecase.entity.CharacterBO

interface CharacterMapper {
    fun map(input: CharacterDTO): CharacterBO
    fun map(characterId: Int): CharacterDTO
    fun map(input: CharacterBO): CharacterDTO
}