package com.josegrillo.marvelapi.mapper

import com.josegrillo.marvelapi.entity.CharacterVO
import com.josegrillo.usecase.entity.CharacterBO

interface CharacterMapper {
    fun map(input: CharacterBO, isFavorite: Boolean = false): CharacterVO
}