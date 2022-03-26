package com.josegrillo.data.mapper

import com.josegrillo.data.entity.CharacterDTO
import com.josegrillo.usecase.entity.CharacterBO

interface CharacterMapper {
    fun map (input: CharacterDTO): CharacterBO
}