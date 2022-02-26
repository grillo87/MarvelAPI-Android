package com.josegrillo.marvelapi.mapper

import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.usecase.entity.Character

interface CharacterMapper {
    fun map(input: Character, isFavorite: Boolean = false): CharacterVO
}